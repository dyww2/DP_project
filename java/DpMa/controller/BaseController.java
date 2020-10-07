package DpMa.controller;

import DpMa.consts.GoodsConsts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;


public class BaseController {
    Logger logger = LoggerFactory.getLogger(BaseController.class);

    /**
     * 上传文件
     *
     * @param uploadDir 以/开头，并且以/结尾
     * @param f
     * @return
     */
    protected String uploadFile(String uploadDir, MultipartFile f) {
        String realPath = GoodsConsts.getUploadPath();
        String uploadPath = uploadDir + UUID.randomUUID() + "." + f.getContentType().split("/")[1];
        File file = new File(realPath + uploadPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            if (!f.isEmpty()) {
                if (f.getSize() > 0) {
                    f.transferTo(file);
                    logger.info("图片已存到：" + realPath + uploadPath);
                    return uploadPath;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return GoodsConsts.NO_UPLOAD_IMG;
    }

    /**
     * 获取Request对象
     *
     * @return
     */
    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }


    /**
     * 获取Session对象
     *
     * @return
     */
    protected HttpSession getSession() {
        return this.getRequest().getSession();
    }

    /**
     * 获取UserId
     *
     * @return
     */
    protected Integer getUserId() {
        return (Integer) getSession().getAttribute("userId");
    }

}
