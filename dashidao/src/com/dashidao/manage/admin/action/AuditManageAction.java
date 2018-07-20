package com.dashidao.manage.admin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dashidao.core.annotation.SecurityMapping;
import com.dashidao.core.mv.JModelAndView;
import com.dashidao.core.query.support.IPageList;
import com.dashidao.core.security.support.SecurityUserHolder;
import com.dashidao.core.tools.CommUtil;
import com.dashidao.foundation.domain.User;
import com.dashidao.foundation.domain.YunkeAudit;
import com.dashidao.foundation.domain.query.YunkeAuditQueryObject;
import com.dashidao.foundation.service.IGoodsService;
import com.dashidao.foundation.service.ISysConfigService;
import com.dashidao.foundation.service.IUserConfigService;
import com.dashidao.foundation.service.IUserService;
import com.dashidao.foundation.service.IYunkeAuditService;
import com.dashidao.foundation.service.IZTCGoldLogService;
import com.dashidao.jwt.config.ResponseMsg;

/**
 * 审核管理控制器
 */
@Controller
public class AuditManageAction {
	@Autowired
	private ISysConfigService configService;

	@Autowired
	private IUserConfigService userConfigService;

	@Autowired
	private IGoodsService goodsService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IZTCGoldLogService ztcGoldLogService;
	@Autowired
	private IYunkeAuditService yunkeAuditService;

	/**
	 * 云客审核列表
	 * 
	 * @param request
	 * @param response
	 * @param currentPage
	 * @param orderBy
	 * @param orderType
	 * @return
	 */
	@SecurityMapping(display = false, rsequence = 0, title = "云客审核", value = "/admin/yunke_auditlb.htm*", rtype = "admin", rname = "云客审核", rcode = "yunke_auditlb", rgroup = "运营")
	@RequestMapping({ "/admin/yunke_auditlb.htm" })
	public ModelAndView ztc_set(HttpServletRequest request, HttpServletResponse response, String currentPage,
			String orderBy, String orderType) {
		ModelAndView mv = new JModelAndView("/admin/blue/yunke_auditlb.html", this.configService.getSysConfig(),
				this.userConfigService.getUserConfig(), 0, request, response);

		YunkeAuditQueryObject qo = new YunkeAuditQueryObject(currentPage, mv, "createDate", "desc");
		IPageList pList = this.yunkeAuditService.list(qo);
		CommUtil.saveIPageList2ModelAndView("", "", "", pList, mv);
		return mv;
	}

	/**
	 * 云客审核
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping({ "/admin/yunke_audit.htm" })
	@ResponseBody
	public void yunkeaudit(HttpServletRequest request, HttpServletResponse response, String state, String id,String applyId,String userid)
			throws IOException {

		String msString="审核失败！";
		YunkeAudit audit = yunkeAuditService.getObjById(Long.parseLong(id));
		if (audit != null && StringUtils.isNotEmpty(state)) {
			audit.setState(Integer.parseInt(state));
			audit.setAuditDate(new Date());
			audit.setAudit(this.userService.getObjById(Long.parseLong(userid)));
			boolean bl=yunkeAuditService.update(audit);
			if (bl) {
				if(Integer.parseInt(state)==1)//状态0未审核1审核成功2审核失败
				{
					User applyUser = this.userService.getObjById(Long.parseLong(applyId));
					if(applyUser!=null) 
					{//userRole
						applyUser.setUserRole("YUNKE");//将用户角色变成云客 BUYER
						boolean b = this.userService.update(applyUser);
						if(b) 
						{
							msString="审核成功！";
						}
					}
				}else {
					User applyUser = this.userService.getObjById(Long.parseLong(applyId));
					if(applyUser!=null) 
					{
						applyUser.setUserRole("BUYER");//将用户角色变成云客 BUYER
						boolean b = this.userService.update(applyUser);
						if(b) 
						{
							msString="审核成功！";
						}
					}
				}
			}
		}
		PrintWriter printWriter2 = response.getWriter();

		printWriter2.print(ResponseMsg.successWithData(msString));
		printWriter2.flush();
		printWriter2.close();

	}
	
	@SecurityMapping(display = false, rsequence = 0, title = "实名认证审核", value = "/admin/certification_check.htm*", rtype = "admin", rname = "实名认证审核", rcode = "certification_check", rgroup = "运营")
    @RequestMapping({"/admin/certification_check.htm"})
    public ModelAndView ztc_apply_view(HttpServletRequest request, HttpServletResponse response, String id, String currentPage){
        ModelAndView mv = new JModelAndView("admin/blue/certification_check.html",
                                            this.configService.getSysConfig(), this.userConfigService
                                            .getUserConfig(), 0, request, response);

        return mv;
    }

}
