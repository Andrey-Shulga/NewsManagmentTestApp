package com.epam.testapp.action;

import com.epam.testapp.model.News;
import com.epam.testapp.model.NewsForm;
import com.epam.testapp.service.NewsService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.epam.testapp.constant.ConstantHolder.SUCCESS;

public class NewsListAction extends Action {

    private static final String NEWS_LIST_ATTRIBUTE = "newsList";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        NewsService newsService = new NewsService();
        NewsForm newsForm = new NewsForm();
        List<News> newsList = newsService.getAll();
        newsForm.setNewsList(newsList);
        request.setAttribute(NEWS_LIST_ATTRIBUTE, newsList);

        return mapping.findForward(SUCCESS);
    }
}