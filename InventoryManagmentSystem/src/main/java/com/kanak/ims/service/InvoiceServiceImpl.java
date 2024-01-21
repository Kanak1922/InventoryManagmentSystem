package com.kanak.ims.service;

import com.kanak.ims.dto.InvoiceDTO;
import com.kanak.ims.dto.ProductResponseDTO;
import com.kanak.ims.model.Invoice;
import com.kanak.ims.model.Product;
import com.kanak.ims.model.ProductDetails;
import com.kanak.ims.model.Sale;
import com.kanak.ims.repository.InvoiceRepository;
import com.kanak.ims.repository.ProductRepository;
import com.kanak.ims.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository innvoicesRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SaleRepository saleRepository;



    @Override
    public boolean addInvoice(InvoiceDTO invoiceDTO) {

        List<ProductDetails> productDetailsList=
                                    invoiceDTO
                                            .getProductDetailsDTO()
                                            .stream()
                                            .map( (pd) ->{
                                                ProductDetails productDetails=null;
                                                Optional<Product> productOptional=productRepository.findById(pd.getProductPid());
                                                if (productOptional.get().getQuantity()<pd.getQty()){return new ProductDetails();
                                                };
                                                if(productOptional.isPresent() && productOptional.get().getQuantity()>=pd.getQty()) {
                                                    productDetails=new ProductDetails();
                                                    Product p=productOptional.get();
                                                    p.setQuantity(p.getQuantity()-pd.getQty());
                                                    if(p.getQuantity()==0){
                                                        p.setStatus("inactive");
                                                    }
                                                    productRepository.save(p);
                                                    productDetails.setProduct(p);
                                                    productDetails.setQty(pd.getQty());
                                                }
                                                return productDetails;
                        }).collect(Collectors.toList());
        if(productDetailsList.size()==1 && productDetailsList.get(0).getQty()==null)return false;
        Invoice invoice=new Invoice();
        invoice.setCustomerName(invoiceDTO.getCustomerName());
        invoice.setInnvoiceDate(invoiceDTO.getInvoiceDate());
        invoice.setProductDetails(productDetailsList);
        Invoice invoice1=innvoicesRepository.save(invoice);
        if(invoice1==null)return false;
        isBillPaid(invoice);
        return true;
    }

    public void isBillPaid(Invoice invoice){
        System.out.println("IsBillPaid : ");
        Scanner sc=new Scanner(System.in);
//        Boolean res=sc.nextBoolean();
        Boolean res=true;
        if(res==true){
            invoice.setIsBillPaid("yes");
            innvoicesRepository.save(invoice);
            invoiceProfit(invoice);
        }
        else{
            invoiceLoss(invoice);
        }
    }

    public void invoiceProfit(Invoice invoice){
        Sale sale=new Sale();
        List<ProductDetails> productDetailsList=invoice.getProductDetails();
        Double profit=0d;
        for(ProductDetails productDetails :productDetailsList){
            profit+=(productDetails.getProduct().getSellingPrice()-productDetails.getProduct().getPurchasePrice())*(productDetails.getQty());
        }
        sale.setInvoice(invoice);
        sale.setProfit(profit);
        sale.setLoss(0d);
        saleRepository.save(sale);
    }

    public void invoiceLoss(Invoice invoice){
        Sale sale=new Sale();
        List<ProductDetails> productDetailsList=invoice.getProductDetails();
        Double profit= 0d;
        for(ProductDetails productDetails :productDetailsList){
            profit+=(productDetails.getProduct().getSellingPrice()-productDetails.getProduct().getPurchasePrice())*(productDetails.getQty());
        }
        sale.setInvoice(invoice);
        sale.setProfit(0d);
        sale.setLoss(profit);
        saleRepository.save(sale);
    }

    @Override
    public List<ProductResponseDTO> getTodayInnvoiceDetails() {
        Set<Invoice> invoiceSet=innvoicesRepository.findByInnvoiceDate(LocalDate.now());
        List<ProductResponseDTO> pdt=new ArrayList<>();
        for(Invoice invoice:invoiceSet){
            List<ProductResponseDTO> list=
                    invoice.getProductDetails().stream().map(pd->{
                        ProductResponseDTO dto=new ProductResponseDTO();
                        dto.setProductName(pd.getProduct().getName());
                        dto.setBatchNo(pd.getProduct().getBatchNo());
                        dto.setQty(pd.getQty());
                        return dto;
                    }).toList();

            list.forEach(dto->{
                dto.setInvoiceDate(invoice.getInnvoiceDate());
                dto.setCustName(invoice.getCustomerName());
            });

            pdt.addAll(list);
        }
        return pdt;
    }

    @Override
    public List<ProductResponseDTO> getCustomInvoiceDetails(LocalDate startDate, LocalDate endDate) {
        Set<Invoice> invoiceSet=innvoicesRepository.findByInnvoiceCustomDate(startDate,endDate);
        List<ProductResponseDTO> pdt=new ArrayList<>();
        for(Invoice invoice:invoiceSet){
            List<ProductResponseDTO> list=
                    invoice.getProductDetails().stream().map(pd->{
                        ProductResponseDTO dto=new ProductResponseDTO();
                        dto.setProductName(pd.getProduct().getName());
                        dto.setBatchNo(pd.getProduct().getBatchNo());
                        dto.setQty(pd.getQty());
                        return dto;
                    }).toList();

            list.forEach(dto->{
                dto.setInvoiceDate(invoice.getInnvoiceDate());
                dto.setCustName(invoice.getCustomerName());
            });

            pdt.addAll(list);
        }
        return pdt;
    }

    @Override
    public List<ProductResponseDTO> getYearlyInvoiceDetails(int year) {
        Set<Invoice> invoiceSet=innvoicesRepository.findByInnvoiceYear(year);
        List<ProductResponseDTO> pdt=new ArrayList<>();
        for(Invoice invoice:invoiceSet){
            List<ProductResponseDTO> list=
                    invoice.getProductDetails().stream().map(pd->{
                        ProductResponseDTO dto=new ProductResponseDTO();
                        dto.setProductName(pd.getProduct().getName());
                        dto.setBatchNo(pd.getProduct().getBatchNo());
                        dto.setQty(pd.getQty());
                        return dto;
                    }).toList();

            list.forEach(dto->{
                dto.setInvoiceDate(invoice.getInnvoiceDate());
                dto.setCustName(invoice.getCustomerName());
            });

            pdt.addAll(list);
        }
        return pdt;
    }
}
