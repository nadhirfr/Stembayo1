package id.ayobantu.simple.Adapter;

/**
 * Created by fachrul on 4/3/17.
 */

import android.view.View;

/**
 * A click listener for items.
 */
public interface RecyclerOnItemClickListener {
    /**
     * Called when an item is clicked.
     *
     * @param childView View of the item that was clicked.
     * @param position  Position of the item that was clicked.
     */
    void onItemClick(String TAG, View childView, int position);
}
