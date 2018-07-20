package com.dashidao.view.web.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dashidao.core.domain.virtual.SysMap;
import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.tools.WebForm;
import com.dashidao.foundation.domain.Certification;
import com.dashidao.foundation.domain.User;
import com.dashidao.foundation.domain.query.CertificationQueryObject;
import com.dashidao.foundation.service.ICertificationService;
import com.dashidao.foundation.service.IUserService;
import com.dashidao.jwt.filter.SignFilter;
import com.dashidao.jwt.util.ComplexOutPropertyPreFilter;
import com.dashidao.jwt.util.DateJsonValueProcessor;

import io.jsonwebtoken.Claims;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

/**
 * 实名认证控制器
 * 
 * @author lsp
 *
 */
@Controller
public class CertificationViewAction {
	
	@Autowired
	private ICertificationService certificationService;
	
	// 根据用户id查询实名认证
	@RequestMapping({ "/wap/certification.htm" })
	@ResponseBody
	public void certification(HttpServletRequest request, HttpServletResponse response, int user_id)
			throws IOException {
		String currentPage = request.getParameter("currentPage");
		Map params = new HashMap();
		Claims claims = SignFilter.checkSign(request, response);
		if (claims != null) {
			String id = claims.getId();
			//CertificationQueryObject ofqo = new CertificationQueryObject(currentPage, params, "addTime", "desc");
			//ofqo.addQuery("obj.user.id", new SysMap("user_id", Long.parseLong(id)), "=");
			params.put("user_id", user_id);
			List<Certification> lcertification = this.certificationService.query("select obj from Certification obj where obj.user_id=:user_id", params, -1, -1);
		 	   
			//IPageList pList = this.certificationService.list(ofqo);
			//Certification certification = this.certificationService.getObjById(user_id);

			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
			// 需要过滤的类 + 属性
			Map<Class<?>, Set<String>> includeMap = new HashMap<Class<?>, Set<String>>();
			Set<String> set = new HashSet<String>();
			set.add("id");
			set.add("name");
			set.add("id_number");
			set.add("certification_type");
			set.add("certification_status");
			set.add("company_name");
			set.add("business_license");
			set.add("id_card_front");
			set.add("id_card_reverse");
			set.add("business_phone");
			set.add("user_id");
			set.add("addTime");
			set.add("deleteStatus");
			includeMap.put(Certification.class, set);

			jsonConfig.setJsonPropertyFilter(new ComplexOutPropertyPreFilter(includeMap));

			JSONArray json = JSONArray.fromObject(lcertification, jsonConfig);
			SignFilter.print(request, response, json);
		}

	}
	
	
		// 实名认证保存
		@RequestMapping({ "/wap/certificationsave.htm" })
		@ResponseBody
		public void certificationsave(HttpServletRequest request, HttpServletResponse response) throws IOException {
			String currentPage = request.getParameter("currentPage");
			
			Map params = new HashMap();
			Claims claims = SignFilter.checkSign(request, response);
			if (claims != null) {
				WebForm wf = new WebForm();
				Certification certification = null;
				certification = (Certification) wf.toPo(request, Certification.class);
				certification.setAddTime(new Date());
				
				this.certificationService.save(certification);
				SignFilter.print(request, response, certification);
			}

		}

}
