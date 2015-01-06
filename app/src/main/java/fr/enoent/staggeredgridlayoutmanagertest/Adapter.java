package fr.enoent.staggeredgridlayoutmanagertest;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
	@SuppressWarnings("unused")
	private static final String TAG = Adapter.class.getSimpleName();

	private static final int ITEM_COUNT = 3;
	private List<Item> items;

	public Adapter() {
		super();

		items = new ArrayList<>();
		items.add(new Item("Item 1", true));
		for (int i = 2; i <= ITEM_COUNT; ++i) {
			items.add(new Item("Item " + i, false));
		}
	}
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		final Item item = items.get(position);

		holder.textView.setText(item.title);

		final ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
		if (lp instanceof StaggeredGridLayoutManager.LayoutParams) {
			StaggeredGridLayoutManager.LayoutParams sglp = (StaggeredGridLayoutManager.LayoutParams) lp;
			sglp.setFullSpan(item.isActive);
			holder.itemView.setLayoutParams(sglp);
		}
	}

	@Override
	public int getItemCount() {
		return items.size();
	}

	private int findById(int id) {
		for (int i = 0; i < items.size(); ++i) {
			if (items.get(i).title.equals("Item " + id)) {
				return i;
			}
		}

		return -1;
	}

	public void activateItem(int id, boolean active) {
		final int idx = findById(id);
		final Item item = items.get(idx);

		if (active != item.isActive) {
			item.isActive = active;
			notifyItemChanged(idx);
		}
	}

	public void moveItem(int id, int position) {
		final int idx = findById(id);
		final Item item = items.get(idx);

		if (position != idx) {
			Log.d(TAG, "items before:");
			for (int i = 0; i < items.size(); ++i) {
				Log.d(TAG, i + ": " + items.get(i).title);
			}
			items.remove(idx);
			items.add(position, item);
			Log.d(TAG, "items after:");
			for (int i = 0; i < items.size(); ++i) {
				Log.d(TAG, i + ": " + items.get(i).title);
			}
			Log.d(TAG, "Notify: moving from " + idx + " to " + position);
			notifyItemMoved(idx, position);
			//notifyDataSetChanged();
		}
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		TextView textView;

		public ViewHolder(View itemView) {
			super(itemView);
			textView = (TextView) itemView;
		}
	}
}
