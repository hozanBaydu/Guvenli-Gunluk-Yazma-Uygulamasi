package com.hozanbaydu.adobe;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.Display;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.rpc.context.AttributeContext;
import com.hozanbaydu.adobe.databinding.ActivityPasswordBinding;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;


public class PasswordActivity extends AppCompatActivity {

    private ActivityPasswordBinding binding;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    ArrayList<Model> passwordArrayList;
    Adapter RecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPasswordBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        getSupportActionBar().hide();
        passwordArrayList = new ArrayList<Model>();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();


        getFromFirebase();








        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapter = new Adapter(passwordArrayList);
        binding.recyclerView.setAdapter(RecyclerAdapter);
    }


    public void addButton (View view){

        Intent intent = new Intent(PasswordActivity.this,UploadActivity.class);
        startActivity(intent);
        finish();
    }

    public void getFromFirebase(){


       String email= Objects.requireNonNull(Objects.requireNonNull(firebaseAuth.getCurrentUser()).getEmail()).toString();
        System.out.println(email);


        CollectionReference collectionReference = firebaseFirestore.collection("Posts");

        collectionReference.orderBy("date", Query.Direction.DESCENDING).whereEqualTo("useremail",email).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e != null) {
                    Toast.makeText(PasswordActivity.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
                }

                if (queryDocumentSnapshots != null) {

                    for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {

                        Map<String,Object> data = snapshot.getData();

                        //Casting
                        String name = (String) data.get("name");
                        String userEmail = (String) data.get("useremail");
                        String password = (String) data.get("password");

                        Model post = new Model(name,password,userEmail);

                        passwordArrayList.add(post);

                    }
                    RecyclerAdapter.notifyDataSetChanged();

                }

            }
        });


    }
}


