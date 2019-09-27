package com.example.googlebooksearch.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.util.Linkify;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.googlebooksearch.Model.BookItem;
import com.example.googlebooksearch.Model.ImageLinksAPI;
import com.example.googlebooksearch.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;


public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BooksViewHolder> {
    private List<BookItem> mbooksList;
    private Context mcontext;

  public  BooksAdapter(List<BookItem> booksList, Context context){
        this.mbooksList = booksList;
        this.mcontext = context;

    }

    @NonNull
    @Override
    public BooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.book_row_item,parent,false);
        return new BooksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksViewHolder holder, int position) {
        BookItem bookItem = mbooksList.get(position);
        String T = "Title : " + bookItem.getVolumeInfo().getTitle();
        String Desc = "Description :" + bookItem.getVolumeInfo().getDescription();
        holder.title.setText(T);
        holder.Description.setText(Desc);
        String author;
        String selfLink = bookItem.getSelfLink();
        List<String> authors = bookItem.getVolumeInfo().getAuthors();
        if (authors != null) {
            author = "Author :" + authors.get(0);
            holder.Author.setText(author);
        }
        else {
            holder.Author.setText("Author not found");
        }
        Double rating = bookItem.getVolumeInfo().getAverageRating();
        String ratings = "Rating :" + rating.toString();
       holder.rating.setText(ratings);
       holder.selfLink.setText(selfLink);
       // Linkify.addLinks(holder.selfLink,Linkify.WEB_URLS);
/*
       holder.selfLink.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               TextView tv = view.findViewById(R.id.selfLink);
               String s = tv.getText().toString();
               Uri u = Uri.parse(s);
               Intent i = new Intent(Intent.ACTION_VIEW,u);
               i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               mcontext.startActivity(i);
           }
       });

*/
        ImageLinksAPI image = bookItem.getVolumeInfo().getImageLinks();
       String imageLink = image.getThumbnail();
      // String imageLink = "http://books.google.com/books/content?id=IClpljgfEZEC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api";
       imageLink = imageLink.substring(0,4) + "s" + imageLink.substring(4);
       Log.d("myapp",imageLink);

       ImageView bookImage = holder.itemView.findViewById(R.id.book_image);
      // bookImage.setImageURI(Uri.parse(imageLink));
        Picasso.get().load(Uri.parse(imageLink)).placeholder(R.drawable.ic_launcher_background).fit().into(bookImage);
      // holder.image.setImageURI(null);
       // bookImage.setImageURI(Uri.parse(url));

        //holder.image.setImageURI(imageUri);


    }

    @Override
    public int getItemCount() {
      if (mbooksList == null){
          return 0;
      }else
        return mbooksList.size();
    }
/*
    @Override
    public void onClick(View view) {
      TextView tv = view.findViewById(R.id.selfLink);
        String s = tv.getText().toString();
        Uri u = Uri.parse(s);
        Intent i = new Intent(Intent.ACTION_VIEW,u);
        mcontext.startActivity(i);
    }
*/
    public static class BooksViewHolder extends RecyclerView.ViewHolder{
        TextView title,Author,Description,rating,selfLink;
        ImageView image;

        public BooksViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.book_image);
            title = itemView.findViewById(R.id.title);
          //  bookName = itemView.findViewById(R.id.book_name);
            Author = itemView.findViewById(R.id.author);
            Description = itemView.findViewById(R.id.description);
            rating =itemView.findViewById(R.id.rating);
            selfLink = itemView.findViewById(R.id.selfLink);

        }
    }
}
