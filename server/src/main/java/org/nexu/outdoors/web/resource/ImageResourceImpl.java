package org.nexu.outdoors.web.resource;


import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.nexu.outdoors.web.dao.MediaDao;
import org.nexu.outdoors.web.model.UserFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.io.InputStream;

@Path("image")
@Component
public class ImageResourceImpl {


    @Autowired
    private MediaDao mediaDao;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void uploadFile(@FormDataParam("file") InputStream fileInputStream,
                         @FormDataParam("file") FormDataContentDisposition contentDispositionHeader, UserFile userFile) {
        mediaDao.saveImage(fileInputStream, contentDispositionHeader, userFile);
    }

    @DELETE
    public void delete(Long fileId) {
        mediaDao.deleteImage(fileId);
    }

}
