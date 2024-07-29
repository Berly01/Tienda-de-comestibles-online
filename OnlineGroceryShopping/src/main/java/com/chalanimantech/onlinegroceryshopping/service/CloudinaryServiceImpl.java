package com.chalanimantech.onlinegroceryshopping.service;

import com.cloudinary.Cloudinary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static com.chalanimantech.onlinegroceryshopping.util.constants.AppConstants.TEMP_FILE;
import static com.chalanimantech.onlinegroceryshopping.util.constants.AppConstants.URL_TO_LOWERCASE;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadImage(MultipartFile multipartFile) throws IOException {
        File file = File
                .createTempFile(TEMP_FILE, multipartFile.getOriginalFilename());
        multipartFile.transferTo(file);

        return this.cloudinary.uploader()
                .upload(file, new HashMap())
                .get(URL_TO_LOWERCASE).toString();
    }

    @Override
    public MultipartFile findImageByUrl(String imageUrl) {
        return null;
    }
}
