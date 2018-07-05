import business.DeveloperBusiness;
import model.CommonModel;
import model.ConstantUtil;
import model.DeveloperModel;
import util.GsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "DeveloperServlet", urlPatterns = {
        ConstantUtil.ALL_DEVELOPERS_URL,
        ConstantUtil.QUERY_DELELOPERS_URL,
        ConstantUtil.ADD_DEVELOPER_URL,
        ConstantUtil.UPDATE_DEVELOPER_URL,
        ConstantUtil.DELETE_DEVELOPER_URL})

public class DeveloperServlet extends HttpServlet {

    DeveloperBusiness developerBusiness = new DeveloperBusiness();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //todo post请求交给get处理
        this.doGet(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doDelete(req, resp);
        System.out.println("can't ues delete method");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("url=" + request.getRequestURI());
        request.setCharacterEncoding("UTF-8");
        //设置相应内容
        response.setContentType("text/json,charset=UTF-8");
        String url = request.getRequestURI();
        switch (url) {
            case ConstantUtil.ALL_DEVELOPERS_URL:
                getAllDevelopers(request,response);
                break;
            case ConstantUtil.QUERY_DELELOPERS_URL:
                getDeveloper(request,response);
                break;
            case ConstantUtil.ADD_DEVELOPER_URL:
                addDeveloper(request,response);
                break;
            case ConstantUtil.UPDATE_DEVELOPER_URL:
                updateDeveloper(request,response);
                break;
            case ConstantUtil.DELETE_DEVELOPER_URL:
                deleteDeveloper(request,response);
                break;
            default:
                System.out.println("unsupport url: " + url);
        }
    }


    private void getAllDevelopers(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        PrintWriter writer = response.getWriter();
        List<DeveloperModel> developerModelList = developerBusiness.getAllDeveloperModels();
        CommonModel commonModel = new CommonModel();
        if(developerModelList.size()>0){
            commonModel.setSuccess();
            commonModel.setData(developerModelList);
        }else{
            commonModel.setFail();
        }
        writer.println(GsonUtil.bean2Json(commonModel));
        writer.flush();
    }


    private void getDeveloper(HttpServletRequest request, HttpServletResponse response){

    }
    private void addDeveloper(HttpServletRequest request, HttpServletResponse response){

    }
    private void updateDeveloper(HttpServletRequest request, HttpServletResponse response){

    }
    private void deleteDeveloper(HttpServletRequest request, HttpServletResponse response){

    }

}
