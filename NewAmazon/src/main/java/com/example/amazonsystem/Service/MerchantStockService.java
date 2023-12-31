package com.example.amazonsystem.Service;

import com.example.amazonsystem.Model.Merchant;
import com.example.amazonsystem.Model.MerchantStock;
import com.example.amazonsystem.Model.Product;
import com.example.amazonsystem.Repository.MerchantRepository;
import com.example.amazonsystem.Repository.MerchantStockRepository;
import com.example.amazonsystem.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantStockService {

    private final MerchantStockRepository merchantStocks;
    private final MerchantRepository merchantRepository;
    private final ProductRepository productRepository;
    private final MerchantService merchantService;
    private final ProductService productService;
    public Character addMerchantStock(MerchantStock stock) {
        Character a,b;
        a=b=' ';
        for (Product product: productRepository.findAll()) {
            if (product.getId() == stock.getProductId()){
                stock.setProductId(product.getId());
                a ='B';
                break;
            }
        }

        for (Merchant merchant: merchantRepository.findAll()) {
            if (merchant.getId() == stock.getMerchantId()){
                stock.setMerchantId(merchant.getId());
                b = 'C';
                break;
            }
        }
        if (a == 'B' && b == 'C'){
            merchantStocks.save(stock);
            return 'A';
        }
        if (a != 'B' && b != 'C')
            return 'F';

        return 'L';
    }

    public List<MerchantStock> getMerchantStocks() {
        return merchantStocks.findAll();
    }


    public boolean updateMerchantStock(Integer id, MerchantStock merchantStock) {
        MerchantStock merchantStock1 = merchantStocks.getById(id);
        if (merchantStock1 == null) {
            return false;
        }

        merchantStock1.setStock(merchantStock.getStock());
        merchantStock1 = merchantStock;
        merchantStocks.save(merchantStock1);
        return true;
    }



    public boolean deleteMerchantStock(Integer id){
        MerchantStock merchantStock = merchantStocks.getById(id);
        if (merchantStock == null) {
            return false;
        }
        merchantStocks.delete(merchantStock);
        return true;
    }

    //endpoint 11
    public char addToStock(Integer pId,Integer mId, Integer amount){
        char a,b,c;
        a=' ';
        b= ' ';
        c = ' ';
        for (MerchantStock merchantStock:merchantStocks.findAll()) {
            if (merchantStock.getProductId().equals(pId) && merchantStock.getMerchantId().equals(mId)) {
                merchantStock.setStock(merchantStock.getStock()+amount);
                c ='C';
            }
        }

        for (Product product:productService.getProducts()) {
            if (product.getId().equals(pId)) {
                b= 'B';
            }}
        for (Merchant merchant: merchantService.getMerchants()) {
            if (merchant.getId().equals(mId)){
                a = 'D';
            }
            if (c != 'C' && b !='B' && a != 'D'){
                return 'E';
            }

            if (b != 'B'){
                return 'D';
            }

        }

        if (a != 'D' ){
            return 'F';
        }


        return 'A';

    }
}
