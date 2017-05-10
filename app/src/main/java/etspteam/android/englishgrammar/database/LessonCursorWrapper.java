package etspteam.android.englishgrammar.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import etspteam.android.englishgrammar.database.LessonDatabase.LessonTable;
import etspteam.android.englishgrammar.lesson_recycler_view.Lesson;

public class LessonCursorWrapper extends CursorWrapper {
    public LessonCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Lesson getLesson() {
        String lessonName = getString(getColumnIndex(LessonTable.lessonName));
        String lessonDescription = getString(getColumnIndex(LessonTable.lessonDescription));
        int isFavorite = getInt(getColumnIndex(LessonTable.ISFAVORITE));
        int uuid = getInt(getColumnIndex(LessonTable.UUID));
        return new Lesson(lessonName, lessonDescription, isFavorite == 1, uuid);
    }
}
