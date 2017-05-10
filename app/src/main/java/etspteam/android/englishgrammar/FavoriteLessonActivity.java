package etspteam.android.englishgrammar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import java.util.ArrayList;
import java.util.List;
import etspteam.android.englishgrammar.lesson_recycler_view.Lesson;
import etspteam.android.englishgrammar.lesson_recycler_view.LessonAdapter;
import etspteam.android.englishgrammar.lesson_recycler_view.LessonList;

public class FavoriteLessonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_lesson);
        Toolbar toolbar = (Toolbar) findViewById(R.id.favorite_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        RecyclerView mLessonRecyclerView = (RecyclerView) findViewById(R.id.favorite_lesson_recycler);
        mLessonRecyclerView.setHasFixedSize(true);
        mLessonRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        LessonList lessonList = LessonList.get(this);
        List<Lesson> favoriteLessons = new ArrayList<>();
        for (Lesson lesson: lessonList.getLessons()) {
            if (lesson.isFavorite()) favoriteLessons.add(lesson);
        }
        RecyclerView.Adapter mLessonAdapter = new LessonAdapter(this, favoriteLessons);
        mLessonRecyclerView.setAdapter(mLessonAdapter);
    }

}
