package com.winter.app.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.winter.app.board.FileVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component("fileDownView")
@Slf4j
public class FileDownView extends AbstractView{
	@Value("${app.upload.basePath}")
	private String base;

	@Override	//다운 로드 코드 작성
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
			log.info("=========  FILE DOWN VIEW =========");
			log.info("===== {} ====", model);	//
			log.info("===== {}==== ", request.getRequestURI());	//

			FileVO fileVO = (FileVO)model.get("fileVO");

			String uri = request.getRequestURI();
			
			//폴더명 notice/
			uri = uri.substring(1, uri.lastIndexOf("/")+1);

			File file = new File(base+uri, fileVO.getFileName());

			//응답시 한글 처리
			response.setCharacterEncoding("UTF-8");


			//총 파일 크기
			response.setContentLengthLong(file.length());

			//한글파일명 인코딩
			String oriName = URLEncoder.encode(fileVO.getOriName(), "UTF-8");

			//Header 설정		//Content-Disposition에 attachment,filename 함께 주게 되면 Body에 오는 값을 다운로드 받으라는 뜻
			response.setHeader("Content-Disposition", "attachment;fileName=\""+oriName+"\"");
			response.setHeader("Content-Transfer-Encoding", "binary");	//전송 데이타의 body를 인코딩한 방법

			//Server 의 HDD에서 파일을 읽어 오는 작업
			FileInputStream fi = new FileInputStream(file);

			//읽어 온 파일을 client로 전송
			OutputStream os = response.getOutputStream();

			//전송
			FileCopyUtils.copy(fi, os);
			
			//자원 해제
			os.close();
			fi.close();


	}
}
