package com.example.fcfasfirestore;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.fcfasfirestore.model.User;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class base extends AppCompatActivity {
    protected static final String COLLECTION_USERS = "users";
    protected static final String DOCUMENT_FIREBASE_FCFAS = "FirebaseFcfas";
    protected static final String FIELD_FULL_NAME = "fullName";
    protected static final String FIELD_FIRST_NAME = "firstName";
    protected static final String FIELD_LAST_NAME = "lastName";
    protected static final String FIELD_BORN = "born";
    protected static final String FIELD_EMAIL = "email";
    protected static final String FIELD_WEIGHT = "weight";
    protected static final String FIELD_TAGS = "tags";
    protected static final String FIELD_DATE_TIME = "dateTime";
    protected static final String FIELD_IS_ADMIN = "isAdmin";

    protected Map<String, Object> mFirebaser;
    protected User mFirebaseFcafas;
    protected TextView mTextView;


    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<String> tagsFirebaser = Arrays.asList("iOS", "Android", "Web");
        List<String> tagsFirebaseFcfas= Arrays.asList("Cloud Firestore", "Cloud Functions", "Cloud Messaging");

        Map<String, Object> nestedFirebaser = new HashMap<>();
        nestedFirebaser.put(FIELD_FIRST_NAME, "Anonymous");
        nestedFirebaser.put(FIELD_LAST_NAME, "Unknown");

        Map<String, Object> nestedFirebaseFcfas= new HashMap<>();
        nestedFirebaseFcfas.put(FIELD_FIRST_NAME, "Firebase");
        nestedFirebaseFcfas.put(FIELD_LAST_NAME, "Fcfas");

        mFirebaser = new HashMap<>();
        mFirebaser.put(FIELD_FULL_NAME, nestedFirebaser);
        mFirebaser.put(FIELD_BORN, 1983);
        mFirebaser.put(FIELD_IS_ADMIN, false);
        mFirebaser.put(FIELD_WEIGHT, 43.21);
        mFirebaser.put(FIELD_TAGS, tagsFirebaser);
        mFirebaser.put(FIELD_EMAIL, "anonymous@gmail.com");
        mFirebaser.put(FIELD_DATE_TIME, FieldValue.serverTimestamp());

        mFirebaseFcafas = new User(
                nestedFirebaseFcfas,
                54.32,
                1984,
                "firebasefcfas@gmail.com",
                new Date(),
                tagsFirebaseFcfas,
                true
        );

    }

    protected void showData(DocumentSnapshot documentSnapshot) {
        String id = documentSnapshot.getId();

        User user = documentSnapshot.toObject(User.class);
        assert user != null;
        Map<String, Object> fullName = user.fullName;
        double weight = user.weight;
        long born = user.born;
        String email = user.email;
        Date dateTime = user.dateTime;
        boolean isAdmin = user.isAdmin;
        List<String> tags = user.tags;

        mTextView.setText(String.format("ID: %s", id));
        mTextView.append("\n\n");
        mTextView.append("Name: " + fullName.get(FIELD_FIRST_NAME) + " " + fullName.get(FIELD_LAST_NAME));
        mTextView.append("\n\n");
        mTextView.append("Weight: " + weight);
        mTextView.append("\n\n");
        mTextView.append("Email: " + email);
        mTextView.append("\n\n");
        mTextView.append("Born: " + born);
        mTextView.append("\n\n");
        mTextView.append("DateTime: " + dateTime);
        mTextView.append("\n\n");
        mTextView.append("Tags: " + tags);
        mTextView.append("\n\n");
        mTextView.append("IsAdmin: " + isAdmin);
    }
}
