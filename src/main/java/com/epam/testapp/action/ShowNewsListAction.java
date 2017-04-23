package com.epam.testapp.action;

import com.epam.testapp.model.News;
import com.epam.testapp.model.NewsForm;
import com.epam.testapp.service.NewsService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.epam.testapp.constant.ConstantHolder.SUCCESS;


public class ShowNewsListAction extends Action {

    private static final String NEWS_LIST_ATTRIBUTE = "newsList";
    private static final org.slf4j.Logger log = LoggerFactory.getLogger("ShowNewsListAction");
    @Autowired
    private NewsService newsService;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        NewsForm newsForm = (NewsForm) form;
        List<News> newsList = newsService.getAll();
        log.debug( "form = {}", newsForm);
        newsForm.setNewsList(newsList);

        return mapping.findForward(SUCCESS);
    }


}
