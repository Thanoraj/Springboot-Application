package com.example.Firestore_CO225.Service;

import com.example.Firestore_CO225.Entity.Product;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
@Service
public class ProductService {
    private static final String COLLECTION_NAME="product";
    public String saveProduct(Product product) throws ExecutionException, InterruptedException {
        Firestore dbFirestore= FirestoreClient.getFirestore();

        ApiFuture<WriteResult> collection_future=dbFirestore.collection(COLLECTION_NAME).document(product.getName()  ).set(product);

        return collection_future.get(). getUpdateTime().toString();

    }


    public Product getProductdetails(String name) throws ExecutionException, InterruptedException {
        Firestore dbFirestore= FirestoreClient.getFirestore();

         DocumentReference documentReference  =dbFirestore.collection(COLLECTION_NAME).document(name);

        ApiFuture<DocumentSnapshot> future=documentReference.get();

        DocumentSnapshot document=future.get();

        Product product=null;
        if (document.exists()){
            product=document.toObject(Product.class);
            return product;
        }
        else {
            return null;
        }

    }
}
