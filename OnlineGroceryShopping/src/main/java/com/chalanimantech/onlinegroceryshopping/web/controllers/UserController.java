package com.chalanimantech.onlinegroceryshopping.web.controllers;

import com.chalanimantech.onlinegroceryshopping.domain.models.binding.UserRegisterBindingModel;
import com.chalanimantech.onlinegroceryshopping.domain.models.service.UserServiceModel;
import com.chalanimantech.onlinegroceryshopping.domain.models.view.UsersViewModel;
import com.chalanimantech.onlinegroceryshopping.service.UserService;
import com.chalanimantech.onlinegroceryshopping.web.annotations.PageTitle;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import static com.chalanimantech.onlinegroceryshopping.util.constants.AppConstants.REGISTER;
import static com.chalanimantech.onlinegroceryshopping.util.constants.AppConstants.MODEL;
import static com.chalanimantech.onlinegroceryshopping.util.constants.AppConstants.LOGIN;
import static com.chalanimantech.onlinegroceryshopping.util.constants.AppConstants.ERROR;
import static com.chalanimantech.onlinegroceryshopping.util.constants.AppConstants.USER_PROFILE;
import static com.chalanimantech.onlinegroceryshopping.util.constants.AppConstants.USERS;
import static com.chalanimantech.onlinegroceryshopping.util.constants.AppConstants.USERNAME;
import static com.chalanimantech.onlinegroceryshopping.util.constants.AppConstants.VIEW_MODEL;

@RestController
public class UserController extends BaseController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    @PageTitle(REGISTER)
    public ModelAndView renderRegister(@ModelAttribute(name = MODEL) UserRegisterBindingModel model,
                                       ModelAndView modelAndView) {

        modelAndView.addObject(MODEL, model);

        return view("register", modelAndView);
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid @ModelAttribute(name = MODEL) UserRegisterBindingModel model,
                                 BindingResult bindingResult, ModelAndView modelAndView) {

        if (!model.getPassword().equals(model.getConfirmPassword()) || bindingResult.hasErrors() ||
                this.userService.register(modelMapper.map(model, UserServiceModel.class))==null) {

            modelAndView.addObject(MODEL, model);

            return view("register", modelAndView);
        }
        return redirect("/login");
    }

    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    @PageTitle(LOGIN)
    public ModelAndView login(@RequestParam(required = false) String error, ModelAndView modelAndView) {
        if (error != null) {
            modelAndView.addObject(ERROR, "Error");
        }

        return view("/login", modelAndView);
    }

    @GetMapping("/user/profile/{username}")
    @PreAuthorize("isAuthenticated()")
    @PageTitle(USER_PROFILE)
    public ModelAndView renderProfilePageByUsername(@PathVariable
                                                                String username, ModelAndView modelAndView) {
        UserServiceModel userServiceModel = this.userService.findByUsername(username);
        UsersViewModel usersViewModel = this.modelMapper.map(userServiceModel, UsersViewModel.class);
        modelAndView.addObject(VIEW_MODEL, usersViewModel);

        return view("/profile", modelAndView);
    }

    @GetMapping("/admin/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PageTitle(USERS)
    public ModelAndView renderAllUsersPage() {
        return view("/users-all");
    }

    @PostMapping("/users/edit/role/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView updateUserRole(@PathVariable String id, String role, Principal principal) {

        UserServiceModel currentLoggedUser = this.userService.findByUsername(principal.getName());
        UserServiceModel targetUser = userService.findById(id);
        
        if (role == null){
            return redirect("/user/profile/" + targetUser.getUsername());
        }
        if (currentLoggedUser.getId().equals(id)) {
            return redirect("/user/profile/" + principal.getName());
        }

        try {
            this.userService.updateRole(id, role);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return redirect("/user/profile/" + targetUser.getUsername());
    }

    @GetMapping("/api/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UsersViewModel> allUsers() {

        return this.userService.findAllUsers()
                .stream()
                .map(serviceModel -> this.modelMapper.map(serviceModel, UsersViewModel.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/api/users/find")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UsersViewModel allUsers(@RequestParam(USERNAME) String username) {

        UserServiceModel byUsername = this.userService.findByUsername(username);

        return byUsername == null ? new UsersViewModel()
                : this.modelMapper.map(byUsername, UsersViewModel.class);
    }

    private String htmlEscape(String input){
        input = input.replace("&", "&amp;")
              .replace("<", "&lt;")
              .replace(">", "&gt;")
              .replace("\"", "&quot;");

        return input;
    }
}
