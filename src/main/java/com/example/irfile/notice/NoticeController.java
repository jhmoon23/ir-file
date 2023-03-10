package com.example.irfile.notice;

import com.example.irfile.file.FileUploadService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value="/notice", method= RequestMethod.POST)
public class NoticeController {
    @Autowired
    NoticeService noticeService;
    @Autowired
    FileUploadService fileUploadService;

    // 공지 글 작성
    @RequestMapping("/new")
    public String putUpNotice(HttpServletRequest req,
                              @RequestParam("noticeSubject") String noticeSubject,
                              @RequestParam("noticeContents") String noticeContents,
                              @RequestParam(value="file", required=false) MultipartFile file
    ) throws IOException {
        JSONObject jsonParam = new JSONObject();
        JSONObject json = new JSONObject();

        noticeSubject = noticeSubject.trim();
        jsonParam.put("noticeSubject", noticeSubject);
        jsonParam.put("noticeContents", noticeContents);

        if( file != null) {
             Long fileNo = fileUploadService.saveFile(file);
             jsonParam.put("fileNo", fileNo);
        }

        json = noticeService.putUpNotice(jsonParam);

        return json.toString();
    }

    // 공고 게시글 조회
    @RequestMapping("/list")
    public String getNoticeList(HttpServletRequest req,
                                @RequestParam("searchTitle") String searchTitle,
                                @RequestParam("countsPerPage") String countsPerPage,
                                @RequestParam("pageNumber") String pageNumber
    ) {
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("searchTitle", searchTitle);
        jsonParam.put("countsPerPage", countsPerPage);
        jsonParam.put("pageNumber", pageNumber);

        JSONObject returnVal = noticeService.getNoticeList(jsonParam);
        return returnVal.toString();
    }

    // 공고 상세 조회
    @RequestMapping("/content")
    public String getSingleNotice(HttpServletRequest req,
                                  @RequestParam("seq") String seq
    ) {
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("seq", seq);

        JSONObject returnVal = noticeService.getSingleNotice(jsonParam);
        System.out.println("컨트롤러에서 " + returnVal);
        return returnVal.toString();
    }

    // 페이지
    @RequestMapping("/paging")
    public String getNumberOfPages(HttpServletRequest req,
                                   @RequestParam("searchTitle") String searchTitle,
                                   @RequestParam("countsPerPage") String countsPerPage,
                                   @RequestParam("pageNumber") String pageNumber
    ) {
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("searchTitle", searchTitle);
        jsonParam.put("countsPerPage", countsPerPage);
        jsonParam.put("pageNumber", pageNumber);

        JSONObject json = noticeService.getNumberOfPages(jsonParam);
        return json.toString();
    }

//    @RequestMapping("/revision")
//    public String reviseNotice(HttpServletRequest req,
//                               @RequestParam("seq") String seq,
//                               @RequestParam("noticeSubject") String noticeSubject,
//                               @RequestParam("noticeContents") String noticeContents,
//                               @RequestParam(value="file", required=false) MultipartFile file
//    ) {
//        JSONObject jsonParam = new JSONObject();
//        JSONObject json = new JSONObject();
//        jsonParam.put("seq", seq);
//        jsonParam.put("noticeSubject", noticeSubject);
//        jsonParam.put("noticeContents", noticeContents);
//
//        if( file != null) {
//            Long fileNo = fileUploadService.updateFile(file);
//            jsonParam.put("fileNo", fileNo);
//        } else {
//
//            fileUploadService.deleteFile();
//        }
//
//        json = noticeService.reviseNotice(jsonParam);
//        return json.toString();
//    }
    @RequestMapping("/deletion")
    public String putNoticeDeleteFlag(HttpServletRequest req,
                                      @RequestParam("seq") String seq
    ) {
        JSONObject jsonParam = new JSONObject();
        jsonParam.put("seq", seq);
        JSONObject returnVal = noticeService.putNoticeDeleteFlag(jsonParam);
        return returnVal.toString();
    }
}
