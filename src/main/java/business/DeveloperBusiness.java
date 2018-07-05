package business;

import db.DBHelper;
import model.DeveloperModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeveloperBusiness {

    //获取所有 model
    public List<DeveloperModel> getAllDeveloperModels() {
        List<DeveloperModel> developerModelList = new ArrayList<>();
        String sql = "SELECT * FROM developer";
        DBHelper dbHelper = new DBHelper(sql);
        ResultSet resultSet = null;
        try {
            resultSet = dbHelper.preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String site = resultSet.getString(3);
                String avatar = resultSet.getString(4);
                DeveloperModel developerModel = new DeveloperModel();
                developerModel.setId(id);
                developerModel.setName(name);
                developerModel.setSite(site);
                developerModel.setAvatar(avatar);
                developerModelList.add(developerModel);
            }
            resultSet.close();
            dbHelper.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return developerModelList;
        }
    }


    //获取单个 model
    public DeveloperModel getDeveloper(String developerId) {
        String sql = "select * from developer where id=" + developerId;
        DBHelper dbHelper = new DBHelper(sql);
        DeveloperModel developerModel = null;
        try {
            ResultSet resultSet = dbHelper.preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String site = resultSet.getString(3);
                String avatar = resultSet.getString(4);
                developerModel = new DeveloperModel();
                developerModel.setId(id);
                developerModel.setName(name);
                developerModel.setSite(site);
                developerModel.setAvatar(avatar);
            }
            resultSet.close();
            dbHelper.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return developerModel;
        }
    }

    //新增model
    public boolean addDeveloper(DeveloperModel developerModel) {
        String sql = "INSERT INTO developer(name,site,avatar) VALUES(" +
                "'" + developerModel.getName() + "'" +
                "'" + developerModel.getSite() + "'" +
                "'" + developerModel.getAvatar() + "'" + ");";
        System.out.println(sql);
        DBHelper dbHelper = new DBHelper(sql);
        return execute(dbHelper);
    }


    private boolean execute(DBHelper dbHelper) {
        try {
            boolean isSuccess = dbHelper.preparedStatement.execute();
            dbHelper.close();
            return isSuccess;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateDeveloper(String id,String name){
        String sql = "UPDATE developer SET name='"+name+"' WHERE id="+id;
        System.out.println("sql= "+sql);
        DBHelper dbHelper = new DBHelper(sql);
        try{
            boolean isSuccess = false;
            isSuccess = dbHelper.preparedStatement.execute(sql);
            dbHelper.close();
            return isSuccess;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    //删除model
    public boolean deleteDeveloper(String id){
        String sql = "DELETE FROM developer WHERE id=" +id;
        DBHelper dbHelper = new DBHelper(sql);
        System.out.println("sql= "+sql);
        boolean isSuccess;
        try{
            isSuccess = dbHelper.preparedStatement.execute(sql);
        }catch (SQLException e){
            e.printStackTrace();
            isSuccess = false;
        }
        return isSuccess;
    }

}
