package com.epam.testapp.action;

import com.epam.testapp.form.NewsForm;
import com.epam.testapp.model.News;
import com.epam.testapp.service.NewsService;
import com.epam.testapp.util.DateConverter;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.epam.testapp.constant.ConstantHolder.*;

public class NewsAction extends DispatchAction {

    @Autowired
    private NewsService newsService;

    public ActionForward list(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        NewsForm newsForm = (NewsForm) form;
        List<News> newsList = newsService.getAll();
        newsForm.setNewsList(newsList);

        return mapping.findForward(SHOW_LIST_SUCCESS);
    }

    public ActionForward showNewsForm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        NewsForm newsForm = (NewsForm) form;
        String id = request.getParameter(ID);

        if (id != null) {
            News news = newsService.getById(Long.parseLong(id));
            newsForm.setNews(news);
        }

        return mapping.findForward(SHOW_ADD_FORM_SUCCESS);
    }

    public ActionForward view(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        News news = getNews(request, form);
        request.setAttribute(NEWS_ATTRIBUTE, news);

        return mapping.findForward(SHOW_NEWS_VIEW_SUCCESS);
    }

    public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        getNews(request, form);
        return mapping.findForward(SHOW_ADD_FORM_SUCCESS);
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        String id = request.getParameter(ID);
        News news = newsService.getById(Long.parseLong(id));
        newsService.delete(news);

        return mapping.findForward(DELETE_NEWS_SUCCESS);
    }

    private News getNews(HttpServletRequest request, ActionForm form) {

        NewsForm newsForm = (NewsForm) form;
        String id = request.getParameter(ID);
        News news = newsService.getById(Long.parseLong(id));
        String strDate = DateConverter.getDateToStr(news.getDate());
        news.setStrDate(strDate);
        newsForm.setNews(news);
        return news;
    }

}
