package com.yuri.elearning;

import com.yuri.elearning.data.network.ApiInterface;
import com.yuri.elearning.data.network.NetTool;
import com.yuri.elearning.model.CourseBriefInfo;

import org.junit.Test;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testConnection() {
        ApiInterface apiInterface = NetTool.getNetTool().create(ApiInterface.class);
        apiInterface.queryAllCourses().enqueue(new Callback<List<CourseBriefInfo>>() {
            @Override
            public void onResponse(Call<List<CourseBriefInfo>> call, Response<List<CourseBriefInfo>> response) {
                assert response.isSuccessful();
            }

            @Override
            public void onFailure(Call<List<CourseBriefInfo>> call, Throwable t) {
                assert false;
            }
        });
    }
}