package cn.cash360.advanced.mvvm.demo3.module.local.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

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
