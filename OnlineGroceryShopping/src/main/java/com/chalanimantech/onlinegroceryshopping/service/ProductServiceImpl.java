package com.chalanimantech.onlinegroceryshopping.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chalanimantech.onlinegroceryshopping.domain.entities.Product;
import com.chalanimantech.onlinegroceryshopping.domain.models.service.CategoryServiceModel;
import com.chalanimantech.onlinegroceryshopping.domain.models.service.ProductServiceModel;
import com.chalanimantech.onlinegroceryshopping.util.error.ProductNameAlreadyExistsException;
import com.chalanimantech.onlinegroceryshopping.util.error.ProductNotFoundException;
import com.chalanimantech.onlinegroceryshopping.repository.OfferRepository;
import com.chalanimantech.onlinegroceryshopping.repository.ProductRepository;
import com.chalanimantech.onlinegroceryshopping.validation.ProductValidationService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import static com.chalanimantech.onlinegroceryshopping.util.constants.AppConstants.OFFER_SCHEDULED_DISCOUNT;
import static com.chalanimantech.onlinegroceryshopping.util.constants.ExceptionMessages.INVALID_PRODUCT_EX_MSG;
import static com.chalanimantech.onlinegroceryshopping.util.constants.ExceptionMessages.PRODUCT_ID_DOESNT_EXIST_EX_MSG;
import static com.chalanimantech.onlinegroceryshopping.util.constants.ExceptionMessages.PRODUCT_NAME_EXIST_EX_MSG;
import static com.chalanimantech.onlinegroceryshopping.util.constants.ExceptionMessages.PAGE_NOT_FOUND_EX_MSG;

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
