package com.sarb.homepractice;

import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;


public class HomeFragment extends Fragment {
    RecyclerView recyclerView,recyclerViewCompany;
    ProductAdapter productAdapter;
    List<ProductModel> productModelList=new ArrayList<>();
    CompanyAdapter companyAdapter;

    ArrayList<CompanyModel> companyModelArrayList= new ArrayList<>();
    FirebaseFirestore db;




    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db= FirebaseFirestore.getInstance();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView= view.findViewById(R.id.recycler_ProductHome);
        recyclerViewCompany= view.findViewById(R.id.recycler_CompanyHome);
        loadProduct();
        loadCompany();
    }

    private void loadCompany() {
        db.collection("Company").get().addOnCompleteListener((task) -> {
            if (task.isSuccessful()){
                for (QueryDocumentSnapshot document:task.getResult()){
                    String company = document.getData().get("Name").toString();
                    addArraylist(company);
                }
            }

        });
    }

    private void addArraylist(String company) {
        companyModelArrayList.add(new CompanyModel(company));
        createCompanyRecyclerView(companyModelArrayList);
    }

    private void createCompanyRecyclerView(ArrayList<CompanyModel> companyModelArrayList) {
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getActivity().getApplicationContext(),
                RecyclerView.HORIZONTAL,false);
        recyclerViewCompany.setLayoutManager(layoutManager);
    companyAdapter= new CompanyAdapter( companyModelArrayList,getActivity());
        recyclerViewCompany.setAdapter(companyAdapter);}



    private void loadProduct() {
        db.collection("Products").get().addOnCompleteListener((task) -> {
            if (task.isSuccessful()){
                for (QueryDocumentSnapshot document:task.getResult()){
                    String image =document.getData().get("Image").toString();
                    String name= document.getData().get("Name").toString();
                    String memory= document.getData().get("Memory").toString();
                    String price=document.getData().get("Price").toString();
                    addArraylist(image, name,memory,price);


                }
            }

        });
    }

    private void addArraylist(String image, String name, String memory, String price) {
        productModelList.add(new ProductModel(name,price,memory,image));
        createRecyclerView(productModelList);

    }
    public void createRecyclerView(List<ProductModel> productModelList){
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(getActivity().getApplicationContext(), RecyclerView.VERTICAL,false);
recyclerView.setLayoutManager(layoutManager);
productAdapter= new ProductAdapter(productModelList,getActivity());
recyclerView.setAdapter(productAdapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}