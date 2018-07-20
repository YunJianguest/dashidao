package com.dashidao.view.web.action;

import com.dashidao.core.mv.JModelAndView;
import com.dashidao.foundation.domain.Document;
import com.dashidao.foundation.service.IDocumentService;
import com.dashidao.foundation.service.ISysConfigService;
import com.dashidao.foundation.service.IUserConfigService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ϵͳ���¿�����
 */
@Controller
public class DocumentViewAction {
    @Autowired
    private ISysConfigService configService;

    @Autowired
    private IUserConfigService userConfigService;

    @Autowired
    private IDocumentService documentService;

    @RequestMapping({"/doc.htm"})
    public ModelAndView doc(HttpServletRequest request, HttpServletResponse response, String mark){
        ModelAndView mv = new JModelAndView("doc.html", this.configService
                                            .getSysConfig(), this.userConfigService.getUserConfig(), 1,
                                            request, response);
        Document obj = this.documentService.getObjByProperty("mark", mark);
        mv.addObject("obj", obj);

        return mv;
    }
}




