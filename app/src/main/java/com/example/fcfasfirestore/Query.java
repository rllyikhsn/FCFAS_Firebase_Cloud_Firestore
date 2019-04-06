package com.example.fcfasfirestore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.example.fcfasfirestore.model.User;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Query extends base implements View.OnClickListener {

    private static final String TAG = Query.class.getSimpleName();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference colRefUsers = db.collection(COLLECTION_USERS);
    private ListenerRegistration mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        bindView();

    }

    private void bindView() {
        mTextView = findViewById(R.id.tv);

        findViewById(R.id.btn_realtime_doc).setOnClickListener(this);
        findViewById(R.id.btn_realtime_multi_doc).setOnClickListener(this);
        findViewById(R.id.btn_add_sample).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (mListener != null) {
            mListener.remove();
        }


        switch (v.getId()) {
            case R.id.btn_realtime_doc:
                addSingleDocListener();
                break;

            case R.id.btn_realtime_multi_doc:
                addMultiDocsListener();
                break;

            case R.id.btn_add_sample:
                addSampleData();
                break;
        }

    }

    private void addSampleData() {
        List<String> tags1 = Arrays.asList("Authentication", "Realtime Database", "Cloud Storage for Firebase");
        List<String> tags2 = Arrays.asList("Cloud Firestore", "Cloud Functions", "Cloud Messaging");
        List<String> tags3 = Arrays.asList("TestLab", "Crashlytics", "Performance");
        List<String> tags4 = Arrays.asList("Remote Config", "Dynamic Links", "Invites");

        Map<String, Object> nested1 = new HashMap<>();
        nested1.put(FIELD_FIRST_NAME, "Anonymous");
        nested1.put(FIELD_LAST_NAME, "Unknown");

        Map<String, Object> nested2 = new HashMap<>();
        nested2.put(FIELD_FIRST_NAME, "Firebase");
        nested2.put(FIELD_LAST_NAME, "Fcfas");

        Map<String, Object> nested3 = new HashMap<>();
        nested3.put(FIELD_FIRST_NAME, "Alif");
        nested3.put(FIELD_LAST_NAME, "Nur");

        Map<String, Object> nested4 = new HashMap<>();
        nested4.put(FIELD_FIRST_NAME, "Test");
        nested4.put(FIELD_LAST_NAME, "Test");

        User user1 = new User(nested1, 50.12, 1980, "firebaser@gmail.com", new Date(), tags1, false);
        User user2 = new User(nested2, 55.34, 1985, "firebasefcfas@gmail.com", new Date(), tags2, true);
        User user3 = new User(nested3, 60.56, 1990, "alif@gmail.com", new Date(), tags3, true);
        User user4 = new User(nested4, 65.78, 1995, "test@gmail.com", new Date(), tags4, false);

        colRefUsers.document("Firebaser").set(user1);
        colRefUsers.document("FirebaserFcafas").set(user2);
        colRefUsers.document("Alif").set(user3);
        colRefUsers.document("Test").set(user4);
    }

    private void addSingleDocListener() {
        com.google.firebase.firestore.Query mQuery;
        mQuery = colRefUsers;

        mListener = mQuery.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot querySnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e(TAG, e.getMessage());
                    mTextView.setText(e.getMessage());
                    return;
                } else {
                    mTextView.setText(null);
                }

                if (querySnapshots.getDocuments().size() > 0) {
                    for (DocumentSnapshot document : querySnapshots) {
                        User user = document.toObject(User.class);
                        mTextView.append(document.getId());
                        mTextView.append("\n");
                        mTextView.append("Born: " + user.born);
                        mTextView.append("\n");
                        mTextView.append("Weight: " + user.weight);
                        mTextView.append("\n");
                        mTextView.append("Email: " + user.email);
                        mTextView.append("\n");
                        mTextView.append("isAdmin: " + user.isAdmin);
                        mTextView.append("\n\n");
                    }
                } else {
                    mTextView.setText(R.string.error_no_data);
                }

                for (DocumentChange dc : querySnapshots.getDocumentChanges()) {
                    switch (dc.getType()) {
                        case ADDED:
                            Log.d(TAG, "New: " + dc.getDocument().getId());
                            break;
                        case MODIFIED:
                            Log.d(TAG, "Modified: " + dc.getDocument().getId());
                            break;
                        case REMOVED:
                            Log.d(TAG, "Removed: " + dc.getDocument().getId());
                            break;
                    }
                }
            }
        });
    }


    private void addMultiDocsListener() {
        com.google.firebase.firestore.Query mQuery;
        mQuery = colRefUsers;

        mListener = mQuery.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot querySnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.e(TAG, e.getMessage());
                    mTextView.setText(e.getMessage());
                    return;
                } else {
                    mTextView.setText(null);
                }

                if (querySnapshots.getDocuments().size() > 0) {
                    for (DocumentSnapshot document : querySnapshots) {
                        User user = document.toObject(User.class);
                        mTextView.append(document.getId());
                        mTextView.append("\n");
                        mTextView.append("Born: " + user.born);
                        mTextView.append("\n");
                        mTextView.append("Weight: " + user.weight);
                        mTextView.append("\n");
                        mTextView.append("Email: " + user.email);
                        mTextView.append("\n");
                        mTextView.append("isAdmin: " + user.isAdmin);
                        mTextView.append("\n\n");
                    }
                } else {
                    mTextView.setText(R.string.error_no_data);
                }

                for (DocumentChange dc : querySnapshots.getDocumentChanges()) {
                    switch (dc.getType()) {
                        case ADDED:
                            Log.d(TAG, "New: " + dc.getDocument().getId());
                            break;
                        case MODIFIED:
                            Log.d(TAG, "Modified: " + dc.getDocument().getId());
                            break;
                        case REMOVED:
                            Log.d(TAG, "Removed: " + dc.getDocument().getId());
                            break;
                    }
                }
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        addMultiDocsListener();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if(mListener != null) {
            mListener.remove();
        }
    }
}
