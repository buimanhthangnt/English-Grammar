package etspteam.android.englishgrammar.home_page;

import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;
import java.util.List;

import etspteam.android.englishgrammar.R;
import etspteam.android.englishgrammar.fast_scroller.FastScroller;
import etspteam.android.englishgrammar.lesson_recycler_view.*;

public class LessonListFragment extends Fragment {

    public static FloatingActionMenu mFab;
    private RecyclerView mLessonRecyclerView;

    public LessonListFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lesson_list, container, false);
        mLessonRecyclerView = (RecyclerView) v.findViewById(R.id.lesson_list);
        mLessonRecyclerView.setHasFixedSize(true);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mLessonRecyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter mLessonAdapter;
        LessonList lessonList = LessonList.get(getContext());
        List<Lesson> listLessons = new ArrayList<>();
        for (Lesson lesson : lessonList.getLessons()) {
            if (lesson.getUuid() <= 32) listLessons.add(lesson);
        }
        mLessonAdapter = new LessonAdapter(getContext(), listLessons);
        mLessonRecyclerView.setAdapter(mLessonAdapter);

        FastScroller fastScroller = (FastScroller) v.findViewById(R.id.fastscroll);
        fastScroller.setRecyclerView(mLessonRecyclerView);
        mFab = (FloatingActionMenu) v.findViewById(R.id.menu_red);
        return v;
    }

    public static void hideFloatingMenu() {
        mFab.hideMenuButton(true);
    }

    public static void showFloatingMenu() {
        mFab.showMenuButton(true);
    }

    private static final String BUNDLE_RECYCLER_LAYOUT = "classname.recycler.layout";

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if(savedInstanceState != null)
        {
            Parcelable savedRecyclerLayoutState = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT);
            mLessonRecyclerView.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(BUNDLE_RECYCLER_LAYOUT, mLessonRecyclerView.getLayoutManager().onSaveInstanceState());
    }

    @Override
    public void onResume() {
        ((MainActivity) getActivity()).setToolbarColor(R.color.colorPrimary);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        }
        super.onResume();
    }
}
