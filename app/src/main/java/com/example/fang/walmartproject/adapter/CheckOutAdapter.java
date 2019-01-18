package com.example.fang.walmartproject.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fang.walmartproject.AppController;
import com.example.fang.walmartproject.R;
import com.example.fang.walmartproject.checkOut.CheckOutContract;
import com.example.fang.walmartproject.data.Cart;

import java.text.NumberFormat;

public class CheckOutAdapter extends RecyclerView.Adapter{
    CheckOutContract.CheckOutPresenter mPresenter;
    Cart cart;
    AppController volley;
    static NumberFormat format = NumberFormat.getCurrencyInstance();
    static final String TAG = CheckOutAdapter.class.getSimpleName();

    public CheckOutAdapter(CheckOutContract.CheckOutPresenter mPresenter, Cart cart) {
        this.mPresenter = mPresenter;
        this.cart = cart;
        this.volley = AppController.getInstance();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;

        switch (i){
            case 0:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_checkout_prise,viewGroup,false);

                return new TotalPriseViewHolder(view);

            case 1:
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_checkout_payment,viewGroup,false);

                return new PaymentViewHolder(view);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int type = viewHolder.getItemViewType();
        switch (type){
            case 0:
                bindPriseVH(viewHolder);
                break;
            case 1:
                Log.d(TAG,"type "+type);
                bindPayment(viewHolder);
                break;
        }

    }

    private void bindPayment(RecyclerView.ViewHolder viewHolder) {
        final PaymentViewHolder itemViewHolder1 = (PaymentViewHolder) viewHolder;
        itemViewHolder1.placeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check payment
                //itemViewHolder1.addressEditText.setText();
                if (itemViewHolder1.addressEditText.getText() != null &&
                        itemViewHolder1.billingEditText.getText() != null &&
                        itemViewHolder1.cardNumberEditText.getText() != null &&
                        itemViewHolder1.nameEditText.getText() != null &&
                        itemViewHolder1.monthEditText.getText() != null &&
                        itemViewHolder1.yearEditText.getText() != null &&
                        itemViewHolder1.cvcEditText.getText() != null){
                    String address = itemViewHolder1.addressEditText.getText().toString();
                    String bill = itemViewHolder1.billingEditText.getText().toString();
                    String cardNum = itemViewHolder1.cardNumberEditText.getText().toString();
                    String month = itemViewHolder1.monthEditText.getText().toString();
                    String year  = itemViewHolder1.yearEditText.getText().toString();
                    String cvc = itemViewHolder1.cvcEditText.getText().toString();
//
//                    int monthInt = Integer.parseInt(month);
//                    int yearInt = Integer.parseInt(year);
                    mPresenter.checkPayment(address,bill,cardNum,month,year,cvc);
            }
                //check order
            }
        });

    }


    private void bindPriseVH(RecyclerView.ViewHolder viewHolder) {
        final TotalPriseViewHolder itemViewHolder1 = (TotalPriseViewHolder) viewHolder;
        float subtotal = cart.getTotalPrize();
        float tax = (float) (subtotal*0.08);
        float estTotal = subtotal+tax;
        itemViewHolder1.subTotalTextView.setText(format.format(subtotal));
        itemViewHolder1.taxTextView.setText(format.format(tax));
        itemViewHolder1.shippingTextView.setText(format.format(0));
        itemViewHolder1.estTotalTextView.setText(format.format(estTotal));
        itemViewHolder1.applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemViewHolder1.couponEditText.getText()!=null){
                    //volley request
                    mPresenter.checkCoupon(itemViewHolder1.couponEditText.getText().toString());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class TotalPriseViewHolder extends RecyclerView.ViewHolder{
        TextView subTotalTextView,shippingTextView,taxTextView,estTotalTextView;
        EditText couponEditText;
        Button applyButton;

        public TotalPriseViewHolder(@NonNull View itemView) {
            super(itemView);
            subTotalTextView = itemView.findViewById(R.id.tv_sub_total_checkout);
            shippingTextView = itemView.findViewById(R.id.tv_shipping_checkout);
            taxTextView = itemView.findViewById(R.id.tv_tax_checkout);
            estTotalTextView =itemView.findViewById(R.id.tv_est_total_checkout);
            couponEditText = itemView.findViewById(R.id.et_coupon);
            applyButton = itemView.findViewById(R.id.bt_coupon_apply);
        }
    }

    public class PaymentViewHolder extends RecyclerView.ViewHolder{
        EditText cardNumberEditText, nameEditText,monthEditText,yearEditText,cvcEditText;
        EditText addressEditText, billingEditText;
        Button placeButton;

        public PaymentViewHolder(@NonNull View itemView) {
            super(itemView);
            cardNumberEditText = itemView.findViewById(R.id.et_card_number);
            nameEditText = itemView.findViewById(R.id.et_card_name);
            monthEditText = itemView.findViewById(R.id.card_month);
            yearEditText = itemView.findViewById(R.id.card_year);
            cvcEditText = itemView.findViewById(R.id.card_cvc);
            placeButton = itemView.findViewById(R.id.bt_place);
            addressEditText = itemView.findViewById(R.id.ed_address_checkout);
            billingEditText = itemView.findViewById(R.id.ed_bill_checkout);

        }
    }
}
