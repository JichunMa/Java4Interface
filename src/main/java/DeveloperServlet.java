import business.DeveloperBusiness;
import model.CommonModel;
import util.ConstantUtil;
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
        ConstantUtil.QUERY_DEVELOPERS_URL,
        ConstantUtil.ADD_DEVELOPER_URL,
        ConstantUtil.UPDATE_DEVELOPER_URL,
        ConstantUtil.DELETE_DEVELOPER_URL})

public class DeveloperServlet extends HttpServlet {

    private DeveloperBusiness developerBusiness = new DeveloperBusiness();

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
        response.setContentType("text/json;charset=UTF-8");
        String url = request.getRequestURI();
        //从请求中获取参数
//目前查询user,删除user时使用

        String developerID = request.getParameter("developerID");
        String developerName = request.getParameter("developerName");

        switch (url) {
            case ConstantUtil.ALL_DEVELOPERS_URL:
                getAllDevelopers(request, response);
                break;
            case ConstantUtil.QUERY_DEVELOPERS_URL:
                getDeveloper(request, response, developerID);
                break;
            case ConstantUtil.ADD_DEVELOPER_URL:
                String avatar = request.getParameter("avatar");
                String site = request.getParameter("site");
                addDeveloper(request, response, developerName, avatar, site);
                break;
            case ConstantUtil.UPDATE_DEVELOPER_URL:
                updateDeveloper(request, response, developerID, developerName);
                break;
            case ConstantUtil.DELETE_DEVELOPER_URL:
                deleteDeveloper(request, response, developerID);
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
        if (developerModelList.size() > 0) {
            commonModel.setSuccess();
            commonModel.setData(developerModelList);
        } else {
            commonModel.setFail();
        }
        writer.println(GsonUtil.bean2Json(commonModel));
        writer.flush();
    }


    private void getDeveloper(HttpServletRequest request, HttpServletResponse response, String developerID) {
        try {
            PrintWriter writer = response.getWriter();
            CommonModel commonModel = new CommonModel();
            if (developerID == null || developerID.equals("")) {
                commonModel.setFail();
                commonModel.setMsg("查询失败 需要传入developerID");
            } else {
                DeveloperModel model = developerBusiness.getDeveloper(developerID);
                if (model != null) {
                    commonModel.setSuccess();
                    commonModel.setData(model);
                } else {
                    commonModel.setFail();
                }
            }
            writer.println(GsonUtil.bean2Json(commonModel));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addDeveloper(HttpServletRequest request, HttpServletResponse response, String name,
                              String avatar, String site) {
        try {
            PrintWriter writer = response.getWriter();
            CommonModel commonModel = new CommonModel();
            if (name == null || name.equals("")) {
                commonModel.setFail();
                commonModel.setMsg("添加失败 需要传入非空name");
            } else {
                DeveloperModel developerModel = new DeveloperModel();
                developerModel.setName(name);
                if (avatar == null) {
                    avatar = "";
                }
                developerModel.setAvatar(avatar);
                if (site == null) {
                    site = "";
                }
                developerModel.setSite(site);
                boolean isSuccess = developerBusiness.addDeveloper(developerModel);
                if (isSuccess) {
                    commonModel.setSuccess();
                    commonModel.setMsg("新增用户" + developerModel.getName() + "," + developerModel.getAvatar()
                            + "," + developerModel.getSite());
                } else {
                    commonModel.setFail();
                }
            }
            writer.println(GsonUtil.bean2Json(commonModel));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateDeveloper(HttpServletRequest request, HttpServletResponse response,
                                 String developerID, String name) {
        try {
            PrintWriter writer = response.getWriter();
            CommonModel commonModel = new CommonModel();
            if (developerID == null || developerID.equals("")) {
                commonModel.setFail();
                commonModel.setMsg("更新失败 需要传入developerID");
            } else {
                boolean isSuccess = developerBusiness.updateDeveloper(developerID, name);
                if (isSuccess) {
                    commonModel.setSuccess();
                    commonModel.setMsg("developerID:" + developerID + "更新名字为: " + name);
                } else {
                    commonModel.setFail();
                }
            }
            writer.println(GsonUtil.bean2Json(commonModel));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteDeveloper(HttpServletRequest request, HttpServletResponse response, String developerID) {
        try {
            PrintWriter writer = response.getWriter();
            CommonModel commonModel = new CommonModel();
            if (developerID == null || developerID.equals("")) {
                commonModel.setFail();
                commonModel.setMsg("删除数据 需要传入developerID");
            } else {
                boolean isSuccess = developerBusiness.deleteDeveloper(developerID);
                if (isSuccess) {
                    commonModel.setSuccess();
                    commonModel.setMsg("成功删除developerID为:" + developerID + "的user");
                } else {
                    commonModel.setFail();
                    commonModel.setMsg("删除失败 库中不存在developerID为:" + developerID + "的user");
                }
            }
            writer.println(GsonUtil.bean2Json(commonModel));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
