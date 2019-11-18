package cn.cash360.advanced.mvvm.demo3.module.local.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import cn.cash360.advanced.mvvm.demo3.data.Projects;
import cn.cash360.advanced.mvvm.demo3.module.local.dao.ProjectsDao;

/**
 * @time 2019/9/3 11:41
 * @desc
 */

@Database(entities = {Projects.class},version = 1,exportSchema = false)
public abstract class DB  extends RoomDatabase {
    public abstract ProjectsDao getProjectsDao();

}
