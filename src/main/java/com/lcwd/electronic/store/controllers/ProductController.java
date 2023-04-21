package com.lcwd.electronic.store.controllers;

import com.lcwd.electronic.store.dtos.*;
import com.lcwd.electronic.store.services.FileService;
import com.lcwd.electronic.store.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private FileService fileService;

    @Value("${product.image.path}")
    private String imagePath;

    private Logger  logger= LoggerFactory.getLogger(ProductController.class);
    //create
    @PostMapping
    public ResponseEntity<ProductDto> create(@RequestBody ProductDto productDto) {
        ProductDto productDto1 = productService.create(productDto);
        return new ResponseEntity<>(productDto1, HttpStatus.CREATED);
    }

    //update
    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> update(@RequestBody ProductDto productDto, @PathVariable String productId) {
        ProductDto updatedProduct = productService.update(productDto, productId);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);

    }

    //delete
    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponseMessage> delete(@PathVariable String productId) {
        productService.delete(productId);
        ApiResponseMessage productDeletedSuccessfully = ApiResponseMessage.builder().message("product deleted successfully").status(HttpStatus.OK).success(true).build();
        return new ResponseEntity<>(productDeletedSuccessfully, HttpStatus.OK);
    }

    //get single
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable String productId) {
        ProductDto productDto = productService.get(productId);
        return new ResponseEntity<>(productDto, HttpStatus.CREATED);
    }


    //getAll
    @GetMapping
    public ResponseEntity<PageableResponse<ProductDto>> getALlProduct(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
                                                                      @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
                                                                      @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
                                                                      @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        PageableResponse<ProductDto> productDtoPageableResponse = productService.geAll(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(productDtoPageableResponse, HttpStatus.OK);
    }

    //get All live
    @GetMapping("/live")
    public ResponseEntity<PageableResponse<ProductDto>> getALlLive(@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
                                                                   @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
                                                                   @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
                                                                   @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        PageableResponse<ProductDto> productDtoPageableResponse = productService.getAllLive(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(productDtoPageableResponse, HttpStatus.OK);
    }

    //search
    @GetMapping("/search/{query}")
    public ResponseEntity<PageableResponse<ProductDto>> searchUser(@PathVariable String query,
                                                                   @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
                                                                   @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
                                                                   @RequestParam(value = "sortBy", defaultValue = "title", required = false) String sortBy,
                                                                   @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        PageableResponse<ProductDto> productDtoPageableResponse = productService.searchByTitle(query, pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(productDtoPageableResponse, HttpStatus.OK);

    }
    //upload image
    @PostMapping("/image/{productId}")
    public ResponseEntity<ImageResponse>  uploadImage(@PathVariable String productId , @RequestParam ("productImage")MultipartFile image) throws IOException {
        String fileName = fileService.uploadFile(image, imagePath);
        ProductDto productDto = productService.get(productId);
        productDto.setProductImageName(fileName);
        ProductDto updateProduct = productService.update(productDto, productId);
        ImageResponse response = ImageResponse.builder().imageName(updateProduct.getProductImageName()).message("Product image is successfully uploaded").status(HttpStatus.CREATED).success(true).build();
        return  new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    //serve image
    @GetMapping("/image/{productId}")
    public void serveUsermage(@PathVariable String productId , HttpServletResponse response) throws IOException {
        ProductDto productDto = productService.get(productId);
        logger.info("User image name : {}" , productDto.getProductImageName());
        InputStream resource = fileService.getResource(imagePath, productDto.getProductImageName());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }


}