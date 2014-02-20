package org.nexu.outdoors.web.dao;


import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.jongo.MongoCollection;
import org.nexu.outdoors.web.dao.model.CImage;
import org.nexu.outdoors.web.dao.util.MongoCollectionFactory;
import org.nexu.outdoors.web.model.UserFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;

@Component
public class MediaDao {

    @Autowired
    private MongoCollectionFactory mongoCollectionFactory;

    private MongoCollection imageCollection;

    @PostConstruct
    private void init() {
        imageCollection = mongoCollectionFactory.getCollection(CImage.class);
    }

    public CImage saveImage(InputStream fileInputStream, FormDataContentDisposition contentDispositionHeader, UserFile userFile) {
        CImage cImage = new CImage(null, userFile.getActivity().getName(), "", 0, 0, "");
        imageCollection.save(cImage);
        return cImage;
    }


    public void deleteImage(Long fileId) throws IllegalAccessException {
        throw new IllegalAccessException("not implemented yet");
    }
}
