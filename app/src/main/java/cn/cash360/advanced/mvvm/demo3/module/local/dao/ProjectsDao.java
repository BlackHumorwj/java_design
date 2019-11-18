package cn.cash360.advanced.mvvm.demo3.module.local.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import cn.cash360.advanced.mvvm.demo3.data.Projects;

/**
 * @time 2019/9/3 11:35
 * @desc
 */

@Dao
public interface ProjectsDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long add(Projects projects);


    @Query("select * from projects where page=:page")
    LiveData<Projects> queryProjects(int page);

}
