
// custom view

package com.example.snippet.multi_touchgesture;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class CustomView extends View {

    private final float RADIUS = 50.0f;
    private Paint paint;

    GestureArray data;

    // constructor
    public CustomView(Context context) {
        super(context);

        paint = new Paint();

        data = new GestureArray();
    }

    // render screen
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int i;
        GestureArray.GestureItem item;

        // save current matrix
        canvas.save();

        // draw circle
        paint.setColor(0xff0000ff);

        for(i=0;i<data.size();i++){
            item = data.get(i);
            canvas.drawCircle(item.x, item.y, RADIUS, paint);
        }

        // draw text
        paint.setTextSize(30.0f);
        paint.setColor(0xff000000);
        String str = String.format("%d", data.size());
        canvas.drawText(str, 100, 100, paint);

        // restore the matrix saved above
        canvas.restore();
    }

    // screen resolution
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    // touch screen event
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        final int action = event.getActionMasked();

        switch (action){
            case MotionEvent.ACTION_DOWN:         onActionDown(event);        break;
            case MotionEvent.ACTION_UP:           onActionUp(event);          break;
            case MotionEvent.ACTION_MOVE:         onActionMove(event);        break;
            case MotionEvent.ACTION_POINTER_DOWN: onActionPointerDown(event); break;
            case MotionEvent.ACTION_POINTER_UP:   onActionPointerUp(event);   break;
            default: return super.onTouchEvent(event);
        }

        return true;
    }

    // first down gesture
    private void onActionDown(MotionEvent event){

        int index, id;
        float x, y;

        // get position of down gesture
        index = event.getActionIndex();
        id = event.getPointerId(index);
        x = event.getX(index);
        y = event.getY(index);

        // add them to the end of an array
        data.add(id, x, y);

        // refresh display
        invalidate();
    }

    // last up gesture
    private void onActionUp(MotionEvent event){

        int index, id;

        // get the id of up gesture
        index = event.getActionIndex();
        id = event.getPointerId(index);

        // remove item from an array that match the id
        data.remove(id);

        // refresh display
        invalidate();
    }

    // move gesture
    private void onActionMove(MotionEvent event){

        int i, index;
        GestureArray.GestureItem item;

        // update every item in the array with new position
        for(i=0;i<data.size();i++){
            item = data.get(i);
            index = event.findPointerIndex(item.id);
            item.x = event.getX(index);
            item.y = event.getY(index);
        }

        // refresh display
        invalidate();
    }

    // the succeeding down gesture after the first down gesture
    private void onActionPointerDown(MotionEvent event){

        int index, id;
        float x, y;

        // get position of down gesture
        index = event.getActionIndex();
        id = event.getPointerId(index);
        x = event.getX(index);
        y = event.getY(index);

        // add them to the end of an array
        data.add(id, x, y);

        // refresh display
        invalidate();
    }

    // the succeeding up gesture before the last up gesture
    private void onActionPointerUp(MotionEvent event){

        int index, id;

        // get the id of up gesture
        index = event.getActionIndex();
        id = event.getPointerId(index);

        // remove item from an array that match the id
        data.remove(id);

        // refresh display
        invalidate();
    }

}
