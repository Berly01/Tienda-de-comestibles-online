# Tienda de Comestibles Online

Una tienda de comestibles online es un sistema que registra usuarios, categorías, productos, gestiona pedidos y crea ofertas para productos con descuento aleatorios que cambian después de un tiempo determinado

## Propósito del proyecto

Desarrollar una tienda online para Tottus.

## Tecnologías 

| Tecnologia  | Versión |
| ------------- | ------------- |
| Java | JDK21  |
| Maven | 4.0.0 |
| Spring boot  | 2.7.18  |
| Thymeleaf    | 3.1 |
| Mysql | 8.0.33 |
| Mysql-connector-java | 8.0.33 |

## Herramientas Extras
* ModelMapper
* Cloudinary 

## Entorno de ejecución
* Windows 10 x64
* Spring Tool Suite - 4
* Java - 21.0.3

## Domain Driven Design

Entidades: Category, Offer, Order, OrderProduct, Product, Receipt, Role, User.

Objetos de valor:

Fabricas: 

Repositorios: CategoryRepository, OfferRepository, OrderRepository, ProductRepository, ReceiptRepository, 
UserRepository, UserRoleRepository.

Servicios: CategoryServiceImpl, OfferServiceImpl, 
OrderServiceImpl, ProductServiceImpl, ReceiptServiceImpl, UserServiceImpl.

### Arquitectura en Capas, DDD y SOLID
![Main](https://github.com/user-attachments/assets/776d164f-fc7d-49ef-8b1b-97bfae6108d7)

## Patrón de Arquitectura

### Modelo-Vista-Controlador (MVC):

![111_page-0001](https://github.com/user-attachments/assets/065e8334-5c09-40e0-827f-7304bc890fb9)


## Principios SOLID

### Principio de responsabilidad única (SRP)
Entidad User: Debe contener solo los datos y comportamientos directamente relacionados con la entidad User (por ejemplo, nombre, apellido, correo electrónico, etc.).
UserServiceImpl: Debe manejar la lógica de negocio relacionada con los usuarios, como crear, actualizar, eliminar y recuperar usuarios.
Al separar la entidad User del UserServiceImpl, se asegura que cada clase tenga una única responsabilidad bien definida, mejorando la cohesión y facilitando el mantenimiento.

### Principio de apertura y cierre (OCP)
Las clases deben estar abiertas para la extensión, pero cerradas para la modificación.
Separar la lógica de negocio en UserServiceImpl permite extender la funcionalidad relacionada con los usuarios sin modificar la entidad User. Por ejemplo, se pueden añadir nuevos métodos en UserServiceImpl sin cambiar la estructura de la entidad User.

### Principio de inversión de dependencia (DIP)
Las clases deben depender de interfaces o clases abstractas en lugar de clases y funciones concretas. Las clases como User, Product, Category, etc, heredan y depende de la clase abstracta BaseEntity. Tambien algunas clases como UserController, ProductController, dependen de la clase abstracta BaseController.

#### BaseEntity.java
```java
@MappedSuperclass
public abstract class BaseEntity {
	
    private String id;

    protected BaseEntity() {}

    @Id
    @GeneratedValue(generator = UUID_STRING)
    @GenericGenerator(name = UUID_STRING, strategy = UUID_GENERATOR)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
```
#### BaseController.java
```java
public abstract class BaseController {
	
    protected ModelAndView view(String view, ModelAndView modelAndView) {
       
    	modelAndView.setViewName(view);
        return modelAndView;
    }

    protected ModelAndView view(String view) {
        return this.view(view, new ModelAndView());
    }

    protected ModelAndView redirect(String url){
        return this.view(REDIRECT_BASE_CONTROLLER + url);
    }
}

```
#### User.java
```java
@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

	private static final long serialVersionUID = -5043054487544197746L;
	private String username;
    private String password;
    private String email;
    private String address;
    private Set<Role> authorities;

    public User() {
    	super();
        this.authorities = new HashSet<>();
    }

    @Override
    @Column(name = "username", nullable = false, unique = true)
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    @Column(name = "password", nullable = false)
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "email", nullable = false, unique = true)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "address", nullable = false)
    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    public Set<Role> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }

    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isEnabled() {
        return true;
    }
}
```
#### UserServiceImpl.java
```java
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           UserRoleRepository userRoleRepository,
                           ProductRepository productRepository, ModelMapper modelMapper,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        
    	this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(DEFAULT_USER_NOT_FOUND_EX_MSG));
    }

    @Override
    public UserServiceModel register(UserServiceModel userServiceModel) {
        User userEntity = this.modelMapper.map(userServiceModel, User.class);

        String encodedPassword = bCryptPasswordEncoder.encode(userServiceModel.getPassword());
        userEntity.setPassword(encodedPassword);
        userEntity.setAuthorities(getRolesForRegistration());

        return modelMapper.map(this.userRepository.saveAndFlush(userEntity), UserServiceModel.class);
    }

    @Override
    public List<UserServiceModel> findAllUsers() {
        return this.userRepository.findAll()
                .stream()
                .map(uEntity -> this.modelMapper.map(uEntity, UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserServiceModel findByUsername(String username) {
        User userEntity = this.userRepository.findByUsername(username).orElse(null);

        return userEntity == null ? null
                : this.modelMapper.map(userEntity, UserServiceModel.class);
    }

    @Override
    public UserServiceModel findById(String id) {
        User userEntity = this.userRepository.findById(id).orElse(null);

        return userEntity == null ? null
                : this.modelMapper.map(userEntity, UserServiceModel.class);
    }

    @Override
    public void updateRole(String id, String role) {
        User userEntity = this.userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(DEFAULT_USER_NOT_FOUND_EX_MSG));

        boolean isRootAdmin = userEntity.getAuthorities()
                .stream()
                .map(Role::getAuthority)
                .collect(Collectors.toList())
                .contains(ROOT_ADMIN);
        if (isRootAdmin) {
            throw new IllegalArgumentException(DEFAULT_NOT_AUTHORIZE_EX_MSG);
        }

        updateUserRole(userEntity, role);
        this.userRepository.saveAndFlush(userEntity);
    }

    @Override
    public UserServiceModel findUserByUserName(String username) {
        return this.userRepository.findByUsername(username)
                .map(u -> this.modelMapper.map(u, UserServiceModel.class))
                .orElseThrow(() -> new UsernameNotFoundException(DEFAULT_USER_NOT_FOUND_EX_MSG));
    }


    private void updateUserRole(User userEntity, String role) {
        Set<Role> newRole = new HashSet<>();
        switch (role) {
            case ROLE_USER:
                newRole.add(this.userRoleRepository.findByAuthority(ROLE_USER));
                break;
            case ROLE_MODERATOR:
                newRole.add(this.userRoleRepository.findByAuthority(ROLE_MODERATOR));
                newRole.add(this.userRoleRepository.findByAuthority(ROLE_USER));
                break;
            case ROLE_ADMIN:
                newRole.add(this.userRoleRepository.findByAuthority(ROLE_ADMIN));
                newRole.add(this.userRoleRepository.findByAuthority(ROLE_MODERATOR));
                newRole.add(this.userRoleRepository.findByAuthority(ROLE_USER));
                break;        
            default:
            	System.exit(1);
        }
        userEntity.setAuthorities(newRole);
    }

	private Set<Role> getRolesForRegistration() {
        Set<Role> roles = new HashSet<>();

        if(this.userRepository.count() == 0) {
            roles.add(this.userRoleRepository.findByAuthority(ROOT_ADMIN));
            roles.add(this.userRoleRepository.findByAuthority(ROLE_ADMIN));
            roles.add(this.userRoleRepository.findByAuthority(ROLE_ADMIN));
            roles.add(this.userRoleRepository.findByAuthority(ROLE_MODERATOR));
            roles.add(this.userRoleRepository.findByAuthority(ROLE_USER));
        } else {
            roles.add(this.userRoleRepository.findByAuthority(ROLE_USER));
        }

        return roles;
    }
}
```

#### UserController.java
```java
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
```

#### Product.java
```java
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private List<Category> categories;
    private boolean isDeleted;

    public Product() {
    	super();
    }

    @Column(name = "product_name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "price", nullable = false, columnDefinition = "DECIMAL(10, 2) DEFAULT '0.00'")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "image_url", nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @ManyToMany(targetEntity = Category.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "products_categories",
            joinColumns = @JoinColumn(
                    name = "product_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "category_id",
                    referencedColumnName = "id"
            )
    )
    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    
    public boolean isDeleted() {
        return this.isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
```
#### ProductServiceImpl.java
```java
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final OfferRepository offerRepository;
    private final CategoryService categoryService;
    private final CloudinaryService cloudinaryService;
    private final ProductValidationService productValidation;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(
            ProductRepository productRepository,
            OfferRepository offerRepository, CategoryService categoryService,
            CloudinaryService cloudinaryService, ProductValidationService productValidation,
            ModelMapper modelMapper) {
        
    	this.productRepository = productRepository;
        this.offerRepository = offerRepository;
        this.categoryService = categoryService;
        this.cloudinaryService = cloudinaryService;
        this.productValidation = productValidation;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductServiceModel createProduct(ProductServiceModel productServiceModel, MultipartFile image) throws IOException {
        if(!productValidation.isValid(productServiceModel) || image.isEmpty()) {
            throw new IllegalArgumentException(INVALID_PRODUCT_EX_MSG);
        }
        if (productRepository.findByName(productServiceModel.getName()).orElse(null) != null) {
            throw new ProductNameAlreadyExistsException(PRODUCT_NAME_EXIST_EX_MSG);
        }
        Product product = this.modelMapper.map(productServiceModel, Product.class);

        product.setImageUrl(this.cloudinaryService.uploadImage(image));
        product = this.productRepository.saveAndFlush(product);
       
        if (product == null){
            throw new IllegalArgumentException(INVALID_PRODUCT_EX_MSG);
        }
        
        return this.modelMapper.map(product, ProductServiceModel.class);
    }

    @Override
    public List<ProductServiceModel> findAllProducts() {
        List<Product> products = this.productRepository.findAll();

        return products.stream()
                .map(p -> this.modelMapper.map(p, ProductServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductServiceModel findProductById(String id) {
        return this.productRepository.findById(id)
                .map(p -> {
                    ProductServiceModel productServiceModel = this.modelMapper.map(p, ProductServiceModel.class);
                    this.offerRepository.findByProduct_Id(productServiceModel.getId())
                            .ifPresent(o -> productServiceModel.setDiscountedPrice(o.getPrice()));

                    return productServiceModel;
                }).orElseThrow(() -> new ProductNotFoundException(PRODUCT_ID_DOESNT_EXIST_EX_MSG));
    }

    @Override
    public ProductServiceModel editProduct(String id, ProductServiceModel productServiceModel,
                                           boolean isNewImageUploaded, MultipartFile image) throws IOException {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(PRODUCT_ID_DOESNT_EXIST_EX_MSG));
       
        if(!productValidation.isValid(productServiceModel)) {
            throw new IllegalArgumentException(INVALID_PRODUCT_EX_MSG);       
        }
        
        productServiceModel.setId(id);
        Product update = modelMapper.map(productServiceModel, Product.class);

        if (product == null || update == null){
            throw new ProductNotFoundException(PRODUCT_ID_DOESNT_EXIST_EX_MSG);
        }

        if (isNewImageUploaded){
            update.setImageUrl(this.cloudinaryService.uploadImage(image));
        } else {
            update.setImageUrl(product.getImageUrl());
        }

        this.offerRepository.findByProduct_Id(product.getId())
                .ifPresent(o -> {
                    o.setPrice(product.getPrice().multiply(BigDecimal.valueOf(OFFER_SCHEDULED_DISCOUNT)));
                    this.offerRepository.save(o);
                });

        return this.modelMapper.map(this.productRepository.saveAndFlush(update), ProductServiceModel.class);
    }

    @Override
    public void deleteProduct(String id) {
        Product product = this.productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(PRODUCT_ID_DOESNT_EXIST_EX_MSG));
       
        product.setDeleted(true);
        this.productRepository.save(product);
    }

    @Override
    public List<ProductServiceModel> findAllByCategory(String category) {
        List<String> categories = this.categoryService.findAllCategories()
                .stream().map(CategoryServiceModel::getName).collect(Collectors.toList());
        if (!categories.contains(category)){
            throw new SecurityException(PAGE_NOT_FOUND_EX_MSG);
        }

        return this.productRepository.findAll()
                .stream()
                .filter(product -> product.getCategories()
                        .stream().anyMatch(categoryStream -> categoryStream.getName().equals(category)))
                .map(product -> this.modelMapper.map(product, ProductServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductServiceModel> findAllFilteredProducts() {
        return findAllProducts()
                .stream()
                .filter(p -> !p.isDeleted())
                .filter(p -> p.getCategories().stream().anyMatch(c -> !c.isDeleted()))
                .map(p -> {
                    ProductServiceModel productServiceModel = modelMapper.map(p, ProductServiceModel.class);
                    offerRepository.findByProduct_Id(productServiceModel.getId())
                            .ifPresent(o -> productServiceModel.setDiscountedPrice(o.getPrice()));

                    return productServiceModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductServiceModel> findAllByCategoryFilteredProducts(String category) {
        return findAllByCategory(category)
                .stream()
                .filter(p -> !p.isDeleted())
                .filter(p -> p.getCategories().stream().anyMatch(c -> !c.isDeleted()))
                .map(p -> {
                    ProductServiceModel productServiceModel = modelMapper.map(p, ProductServiceModel.class);
                    offerRepository.findByProduct_Id(productServiceModel.getId())
                            .ifPresent(o -> productServiceModel.setDiscountedPrice(o.getPrice()));

                    return productServiceModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductServiceModel> findProductsByPartOfName(String name) {
        return findAllFilteredProducts()
                .stream()
                .filter(p->p.getName().toLowerCase().contains(name.toLowerCase()))
                .map(p -> this.modelMapper.map(p, ProductServiceModel.class))
                .collect(Collectors.toList());
    }
}
```

## Clean Code

* Nombres con significado
* Funciones con una sola tarea
* Manejo de errores


## Funcionalidades

### User
Los usuarios pueden agregar productos a su "Carrito" y luego pueden revisar productos y crear pedidos con el estado "Pendiente". Los pedidos de los usuarios son controlados por los administradores (rol = "Administrador" o "Moderador"). Los usuarios pueden ver detalles sobre sus propios pedidos. Cuando se entrega un pedido, un usuario puede adquirirlo, momento en el que se crea un recibo con ese pedido y ese usuario. Los usuarios pueden ver sus recibos y detalles sobre cada recibo. Los administradores (rol = "Administrador", "Moderador") son esencialmente como los usuarios normales. También pueden tener pedidos, que se entregan, adquieren y también tienen recibos. Los administradores también pueden administrar pedidos para un usuario específico.

* Pueden ver todos los pedidos pendientes y pueden enviarlos.
* Pueden ver todos los pedidos enviados y entregarlos.
* Pueden ver todos los pedidos entregados y ver detalles sobre ellos.

La aplicación proporciona a los usuarios invitados (no registrados) la funcionalidad para:

* Iniciar sesión
* Registrarse
* Ver la página de índice de invitados con productos de venta

La aplicación proporciona a los usuarios (registrados) la funcionalidad para:

* Cerrar sesión
* Comprar productos
* Crear pedidos
* Ver sus pedidos
* Ver detalles sobre un pedido
* Ver sus recibos
* Ver detalles sobre un recibo

La aplicación proporciona a los moderadores (que han iniciado sesión y tienen el rol de Moderador) la funcionalidad de:

* Cerrar sesión
* Administrar todos los productos
* Administrar todas las categorías
* Comprar productos
* Crear pedidos
* Ver sus pedidos
* Ver detalles sobre un pedido
* Ver sus recibos
* Ver detalles sobre un recibo
* Ver todos los pedidos pendientes
* Ver todos los pedidos enviados
* Ver todos los pedidos entregados
* Ver detalles sobre todos los pedidos entregados
* Enviar pedidos
* Entregar pedidos

La aplicación proporciona a los administradores (que han iniciado sesión y tienen el rol de administrador) la funcionalidad para:

* Cerrar sesión
* Administrar todos los productos
* Administrar todas las categorías
* Comprar productos
* Crear pedidos
* Ver sus pedidos
* Ver detalles sobre un pedido
* Ver sus recibos
* Ver detalles sobre un recibo
* Ver todos los pedidos pendientes
* Ver todos los pedidos enviados
* Ver todos los pedidos entregados
* Ver detalles sobre todos los pedidos entregados
* Enviar pedidos
* Entregar pedidos

### Productos
Los productos se crean con un Nombre, una Descripción, un Precio, una URL de Imagen (Nube) y Categorías a las que pertenece.

### Ofertas
Las ofertas se crean aleatoriamente utilizando la anotación @Scheduled en Spring con un producto aleatorio y un nuevo precio con descuento.

### Pedidos
Cuando se crean Pedidos, se crean con una lista de Productos, un Precio Total, una Dirección de Envío y un Usuario Destinatario.

* Tras la creación, el estado de un pedido debe establecerse en Pendiente.
* Tras la creación, la fecha de emisión de una orden debe establecerse en la fecha actual.

#### Pedido pendientes
Un pedido pendiente puede ser enviado por un administrador haciendo clic en el botón [Enviar] en la página de pedidos pendientes. En ese momento, el estado del pedido pasa a ser "Enviado" y la fecha del estado (enviado el) debe establecerse en una fecha actual.

* Todas las Órdenes Pendientes se presentan en la Página de Órdenes Pendientes.
* Un usuario puede ver sus Órdenes pendientes en su página Mis órdenes en el bloque rectangular Pendiente.
* Un Usuario puede ver detalles sobre cada una de sus Órdenes Pendientes desde su Página Mis Órdenes, haciendo clic en el botón [Detalles].

#### Pedidos enviados
Un pedido enviado puede ser entregado por un administrador haciendo clic en el botón [Entregar] en la página pedido enviados. En ese momento, el Estado del pedido pasa a ser "Entregado" y la Fecha de estado (Entregado el) debe establecerse en una fecha actual.

* Todos los pedidos enviados se presentan en la página de paquetes enviados.
* Un usuario puede ver sus pedidos enviados en su página de índice en el bloque rectangular enviado.
* Un Usuario puede ver detalles sobre cada uno de sus pedidos Enviados desde su Página de Índice, haciendo clic en el botón [Detalles].

#### Pedidos entregados
Un pedido entregado puede ser adquirido por el destinatario del pedido haciendo clic en el botón [Adquirir] en su página Mis pedidos. En ese momento el Estado del Pedido pasa a ser "Adquirido" y se genera un Recibo al Usuario por ese Pedido. Todos los pedidos entregados se presentan en la página de pedidos entregados. Un Usuario puede ver sus Pedidos Entregados en su Página Mis Pedidos en el bloque rectangular Entregado. Un Usuario puede Adquirir cada uno de sus Pedidos Entregados desde su Página Mis Pedidos, haciendo clic en el botón [Adquirir].

### Recibos
Los recibos son solo entidades de datos. Se crean cuando un Pedido es Adquirido por su Usuario Destinatario.

## Pruebas de APIs (Postman)

PRUEBAS DE APIS:
Este proyecto registra usuarios, administrar productos y categorías, maneja pedidos y ofrece funciones como la creación de ofertas para productos con descuento aleatorio. Estas son algunas de las API y funcionalidades clave utilizadas en el proyecto:

### API de gestión de usuarios: 
Maneja el registro de usuarios, el inicio de sesión, la asignación de roles y la gestión de perfiles. Los usuarios pueden tener roles como "Administrador raíz", "Administrador", "Moderador" o "Usuario", cada uno con permisos específicos.

Registro:
Probar el endpoint de registro de usuario

![image](https://github.com/user-attachments/assets/97175fc3-3e2a-4c83-8323-966b79a6dcbf)

Capturamos la solicitud:

![image](https://github.com/user-attachments/assets/f0334346-f212-426a-b861-82ee7738ff19)

Obtenemos un resultado exitoso con respecto a nuestra request

![image](https://github.com/user-attachments/assets/ee2dfcd4-d733-4b0b-99e4-98ad73a3df3d)

### API de gestión de productos: 
Permite a los administradores administrar productos, incluida la creación, actualización y eliminación de listados de productos. Los productos están asociados con categorías y tienen atributos como nombre, descripción, precio y URL de imagen.

Comprobamos el status al momento de seleccionar un producto 
Se envia el codigo del producto y la cantidad a solicitar

![image](https://github.com/user-attachments/assets/d7a96596-649a-4cf5-8e47-0cfc12f737ba)
![image](https://github.com/user-attachments/assets/427f6de7-4f5c-4045-9fe1-390fd09bdb3e)

Recibimos un status 200 

![image](https://github.com/user-attachments/assets/6387ac71-ed3d-4fd2-afeb-f0e86a175bce)

# API de gestión de pedidos: 
Administra el ciclo de vida de un pedido desde su creación hasta su finalización. Los usuarios pueden crear pedidos, ver sus pedidos y los administradores pueden enviar, entregar y ver los detalles de los pedidos.
Podemos ver los productos solicitados anteriormente

![image](https://github.com/user-attachments/assets/2a356b8c-3062-4c3f-9b3e-51a963fb530c)
![image](https://github.com/user-attachments/assets/31f4cc46-7f00-426c-b91c-5154452671c3)

API de busqueda de productos: La anotación @GetMapping("/api/find") en el método searchProducts en el UserController define un endpoint de API de búsqueda. Este endpoint permite a los usuarios buscar productos basados en una parte del nombre del producto.

![image](https://github.com/user-attachments/assets/56d24e61-cdc7-4255-b10c-a7738f5da978)
![image](https://github.com/user-attachments/assets/2cd544da-2c17-40e8-afcb-c5b8dc3e55e4)

Configuración de seguridad: implementa Spring Security para gestionar la autenticación y la autorización de diferentes roles de usuario, lo que garantiza que solo los usuarios autorizados puedan acceder a funcionalidades específicas.
## Pruebas de Rendimiento (jMeter)
## Pruebas de Seguridad (Zap)

Esta tabla muestra el número de alertas de cada tipo de alerta, junto con el nivel de riesgo del tipo de alerta.
(Los porcentajes entre paréntesis representan cada recuento como un porcentaje, redondeado a un decimal, del número total de alertas incluidas en este informe).

![image](https://github.com/user-attachments/assets/c55b1a3d-f24c-4418-84f4-ed99124e79e0)

## Gestión de Cambios – Issues (Trello)

https://trello.com/b/5EylDJmu
## Documentación de Servicios en base al estándar OpenAPI y

## ScreenShots


