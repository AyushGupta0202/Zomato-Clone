package com.androcid.zomato.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.androcid.zomato.model.room.UserLocation;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserLocationDao {

    @Query("select * from UserLocation")
    LiveData<List<UserLocation>> getAllUserLocations();

    @Query("select * from UserLocation where name like :name order by name")
    LiveData<List<UserLocation>> getUserLocationsByName(String name);

    @Insert(onConflict = REPLACE)
    void addUserLocations(List<UserLocation> userLocations);

    @Delete
    void deleteUserLocation(UserLocation userLocation);

}
