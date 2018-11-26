package com.codingpan.doordashlite;

import android.view.ViewGroup;

import com.codingpan.doordashlite.activities.RestaurantListAdapter;
import com.codingpan.doordashlite.objects.Restaurant;
import com.google.gson.Gson;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class RestaurantListAdapterTest {
    private static final String JSON = "{\n" +
            "\t\"is_time_surging\": false,\n" +
            "\t\"max_order_size\": null,\n" +
            "\t\"delivery_fee\": 0,\n" +
            "\t\"max_composite_score\": 10,\n" +
            "\t\"id\": 443,\n" +
            "\t\"merchant_promotions\": [{\n" +
            "\t\t\"minimum_subtotal_monetary_fields\": {\n" +
            "\t\t\t\"currency\": \"USD\",\n" +
            "\t\t\t\"display_string\": \"$35.00\",\n" +
            "\t\t\t\"unit_amount\": 3500,\n" +
            "\t\t\t\"decimal_places\": 2\n" +
            "\t\t},\n" +
            "\t\t\"delivery_fee\": 0,\n" +
            "\t\t\"delivery_fee_monetary_fields\": {\n" +
            "\t\t\t\"currency\": \"USD\",\n" +
            "\t\t\t\"display_string\": \"$0.00\",\n" +
            "\t\t\t\"unit_amount\": 0,\n" +
            "\t\t\t\"decimal_places\": 2\n" +
            "\t\t},\n" +
            "\t\t\"minimum_subtotal\": 3500,\n" +
            "\t\t\"new_store_customers_only\": false,\n" +
            "\t\t\"id\": 408\n" +
            "\t}, {\n" +
            "\t\t\"minimum_subtotal_monetary_fields\": {\n" +
            "\t\t\t\"currency\": \"USD\",\n" +
            "\t\t\t\"display_string\": \"$0.00\",\n" +
            "\t\t\t\"unit_amount\": null,\n" +
            "\t\t\t\"decimal_places\": 2\n" +
            "\t\t},\n" +
            "\t\t\"delivery_fee\": null,\n" +
            "\t\t\"delivery_fee_monetary_fields\": {\n" +
            "\t\t\t\"currency\": \"USD\",\n" +
            "\t\t\t\"display_string\": \"$0.00\",\n" +
            "\t\t\t\"unit_amount\": null,\n" +
            "\t\t\t\"decimal_places\": 2\n" +
            "\t\t},\n" +
            "\t\t\"minimum_subtotal\": null,\n" +
            "\t\t\"new_store_customers_only\": false,\n" +
            "\t\t\"id\": 3853\n" +
            "\t}, {\n" +
            "\t\t\"minimum_subtotal_monetary_fields\": {\n" +
            "\t\t\t\"currency\": \"USD\",\n" +
            "\t\t\t\"display_string\": \"$0.00\",\n" +
            "\t\t\t\"unit_amount\": null,\n" +
            "\t\t\t\"decimal_places\": 2\n" +
            "\t\t},\n" +
            "\t\t\"delivery_fee\": null,\n" +
            "\t\t\"delivery_fee_monetary_fields\": {\n" +
            "\t\t\t\"currency\": \"USD\",\n" +
            "\t\t\t\"display_string\": \"$0.00\",\n" +
            "\t\t\t\"unit_amount\": null,\n" +
            "\t\t\t\"decimal_places\": 2\n" +
            "\t\t},\n" +
            "\t\t\"minimum_subtotal\": null,\n" +
            "\t\t\"new_store_customers_only\": false,\n" +
            "\t\t\"id\": 6342\n" +
            "\t}, {\n" +
            "\t\t\"minimum_subtotal_monetary_fields\": {\n" +
            "\t\t\t\"currency\": \"USD\",\n" +
            "\t\t\t\"display_string\": \"$0.00\",\n" +
            "\t\t\t\"unit_amount\": null,\n" +
            "\t\t\t\"decimal_places\": 2\n" +
            "\t\t},\n" +
            "\t\t\"delivery_fee\": null,\n" +
            "\t\t\"delivery_fee_monetary_fields\": {\n" +
            "\t\t\t\"currency\": \"USD\",\n" +
            "\t\t\t\"display_string\": \"$0.00\",\n" +
            "\t\t\t\"unit_amount\": null,\n" +
            "\t\t\t\"decimal_places\": 2\n" +
            "\t\t},\n" +
            "\t\t\"minimum_subtotal\": null,\n" +
            "\t\t\"new_store_customers_only\": false,\n" +
            "\t\t\"id\": 6337\n" +
            "\t}, {\n" +
            "\t\t\"minimum_subtotal_monetary_fields\": {\n" +
            "\t\t\t\"currency\": \"USD\",\n" +
            "\t\t\t\"display_string\": \"$0.00\",\n" +
            "\t\t\t\"unit_amount\": null,\n" +
            "\t\t\t\"decimal_places\": 2\n" +
            "\t\t},\n" +
            "\t\t\"delivery_fee\": null,\n" +
            "\t\t\"delivery_fee_monetary_fields\": {\n" +
            "\t\t\t\"currency\": \"USD\",\n" +
            "\t\t\t\"display_string\": \"$0.00\",\n" +
            "\t\t\t\"unit_amount\": null,\n" +
            "\t\t\t\"decimal_places\": 2\n" +
            "\t\t},\n" +
            "\t\t\"minimum_subtotal\": null,\n" +
            "\t\t\"new_store_customers_only\": false,\n" +
            "\t\t\"id\": 6338\n" +
            "\t}, {\n" +
            "\t\t\"minimum_subtotal_monetary_fields\": {\n" +
            "\t\t\t\"currency\": \"USD\",\n" +
            "\t\t\t\"display_string\": \"$0.00\",\n" +
            "\t\t\t\"unit_amount\": null,\n" +
            "\t\t\t\"decimal_places\": 2\n" +
            "\t\t},\n" +
            "\t\t\"delivery_fee\": null,\n" +
            "\t\t\"delivery_fee_monetary_fields\": {\n" +
            "\t\t\t\"currency\": \"USD\",\n" +
            "\t\t\t\"display_string\": \"$0.00\",\n" +
            "\t\t\t\"unit_amount\": null,\n" +
            "\t\t\t\"decimal_places\": 2\n" +
            "\t\t},\n" +
            "\t\t\"minimum_subtotal\": null,\n" +
            "\t\t\"new_store_customers_only\": false,\n" +
            "\t\t\"id\": 6340\n" +
            "\t}, {\n" +
            "\t\t\"minimum_subtotal_monetary_fields\": {\n" +
            "\t\t\t\"currency\": \"USD\",\n" +
            "\t\t\t\"display_string\": \"$0.00\",\n" +
            "\t\t\t\"unit_amount\": null,\n" +
            "\t\t\t\"decimal_places\": 2\n" +
            "\t\t},\n" +
            "\t\t\"delivery_fee\": null,\n" +
            "\t\t\"delivery_fee_monetary_fields\": {\n" +
            "\t\t\t\"currency\": \"USD\",\n" +
            "\t\t\t\"display_string\": \"$0.00\",\n" +
            "\t\t\t\"unit_amount\": null,\n" +
            "\t\t\t\"decimal_places\": 2\n" +
            "\t\t},\n" +
            "\t\t\"minimum_subtotal\": null,\n" +
            "\t\t\"new_store_customers_only\": false,\n" +
            "\t\t\"id\": 7472\n" +
            "\t}, {\n" +
            "\t\t\"minimum_subtotal_monetary_fields\": {\n" +
            "\t\t\t\"currency\": \"USD\",\n" +
            "\t\t\t\"display_string\": \"$0.00\",\n" +
            "\t\t\t\"unit_amount\": null,\n" +
            "\t\t\t\"decimal_places\": 2\n" +
            "\t\t},\n" +
            "\t\t\"delivery_fee\": null,\n" +
            "\t\t\"delivery_fee_monetary_fields\": {\n" +
            "\t\t\t\"currency\": \"USD\",\n" +
            "\t\t\t\"display_string\": \"$0.00\",\n" +
            "\t\t\t\"unit_amount\": null,\n" +
            "\t\t\t\"decimal_places\": 2\n" +
            "\t\t},\n" +
            "\t\t\"minimum_subtotal\": null,\n" +
            "\t\t\"new_store_customers_only\": false,\n" +
            "\t\t\"id\": 7613\n" +
            "\t}],\n" +
            "\t\"average_rating\": 4.7,\n" +
            "\t\"menus\": [{\n" +
            "\t\t\"popular_items\": [{\n" +
            "\t\t\t\"price\": 1400,\n" +
            "\t\t\t\"description\": \"Canadian bacon.\",\n" +
            "\t\t\t\"img_url\": \"https://res.cloudinary.com/doordash/image/fetch/c_fill,w_1200,h_672,q_auto:eco,f_auto/https://doordash-static.s3.amazonaws.com/media/photos/2fab4d9a-3277-4e21-a889-898ad98d2333-retina-large.jpg\",\n" +
            "\t\t\t\"id\": 29268604,\n" +
            "\t\t\t\"name\": \"C1 Classic Eggs Benedict\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"price\": 900,\n" +
            "\t\t\t\"description\": \"\",\n" +
            "\t\t\t\"img_url\": \"https://res.cloudinary.com/doordash/image/fetch/c_fill,w_1200,h_672,q_auto:eco,f_auto/https://doordash-static.s3.amazonaws.com/media/photos/1e415e65-8d18-4146-8d6b-affdab761871-retina-large.jpg\",\n" +
            "\t\t\t\"id\": 29268615,\n" +
            "\t\t\t\"name\": \"A8 Belgium Waffle\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"price\": 1375,\n" +
            "\t\t\t\"description\": \"Tender steak slices, mushrooms, onions & melted Swiss cheese.\",\n" +
            "\t\t\t\"img_url\": \"https://res.cloudinary.com/doordash/image/fetch/c_fill,w_1200,h_672,q_auto:eco,f_auto/https://doordash-static.s3.amazonaws.com/media/photos/6007af37-9897-452d-a961-60a091ad9b30-retina-large.jpg\",\n" +
            "\t\t\t\"id\": 29268454,\n" +
            "\t\t\t\"name\": \"K15 Philly Cheese Steak\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"price\": 1150,\n" +
            "\t\t\t\"description\": \"Topped with three cheeses.\",\n" +
            "\t\t\t\"img_url\": \"https://res.cloudinary.com/doordash/image/fetch/c_fill,w_1200,h_672,q_auto:eco,f_auto/https://doordash-static.s3.amazonaws.com/media/photos/36cb08f4-c2d6-4656-8440-914bc98031f7-retina-large.jpg\",\n" +
            "\t\t\t\"id\": 29268496,\n" +
            "\t\t\t\"name\": \"D1 Tres Quesos\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"price\": 1000,\n" +
            "\t\t\t\"description\": \"\",\n" +
            "\t\t\t\"img_url\": \"https://res.cloudinary.com/doordash/image/fetch/c_fill,w_1200,h_672,q_auto:eco,f_auto/https://doordash-static.s3.amazonaws.com/media/photos/8e95f4e8-20f4-4eb8-8781-5e259b0a3de0-retina-large.jpg\",\n" +
            "\t\t\t\"id\": 29268614,\n" +
            "\t\t\t\"name\": \"A9 French Toast\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"price\": 1150,\n" +
            "\t\t\t\"description\": \"Three eggs scrambled with melted Cheddar cheese.\",\n" +
            "\t\t\t\"img_url\": \"https://res.cloudinary.com/doordash/image/fetch/c_fill,w_1200,h_672,q_auto:eco,f_auto/https://doordash-static.s3.amazonaws.com/media/photos/55801fb4-b0fd-40af-bf9c-558dfffae14b-retina-large.jpg\",\n" +
            "\t\t\t\"id\": 29268476,\n" +
            "\t\t\t\"name\": \"B2 Cheesy Scramble\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"price\": 1400,\n" +
            "\t\t\t\"description\": \"Scramble eggs, avocado, black beans & potato.\",\n" +
            "\t\t\t\"img_url\": \"https://res.cloudinary.com/doordash/image/fetch/c_fill,w_1200,h_672,q_auto:eco,f_auto/https://doordash-static.s3.amazonaws.com/media/photos/cd20c537-4a70-4a48-8516-480ff79ecedb-retina-large.jpg\",\n" +
            "\t\t\t\"id\": 29268471,\n" +
            "\t\t\t\"name\": \"#16 Veggie & Eggs\"\n" +
            "\t\t}],\n" +
            "\t\t\"is_catering\": false,\n" +
            "\t\t\"subtitle\": \"All Day\",\n" +
            "\t\t\"id\": 301078,\n" +
            "\t\t\"name\": \"A Good Morning (All Day) (Los Altos) (DD4B)\"\n" +
            "\t}, {\n" +
            "\t\t\"popular_items\": [{\n" +
            "\t\t\t\"price\": 1400,\n" +
            "\t\t\t\"description\": \"Canadian bacon.\",\n" +
            "\t\t\t\"img_url\": \"https://res.cloudinary.com/doordash/image/fetch/c_fill,w_1200,h_672,q_auto:eco,f_auto/https://doordash-static.s3.amazonaws.com/media/photos/2fab4d9a-3277-4e21-a889-898ad98d2333-retina-large.jpg\",\n" +
            "\t\t\t\"id\": 5573121,\n" +
            "\t\t\t\"name\": \"C1 Classic Eggs Benedict\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"price\": 700,\n" +
            "\t\t\t\"description\": \"\",\n" +
            "\t\t\t\"img_url\": \"https://res.cloudinary.com/doordash/image/fetch/c_fill,w_1200,h_672,q_auto:eco,f_auto/https://doordash-static.s3.amazonaws.com/media/photos/8e95f4e8-20f4-4eb8-8781-5e259b0a3de0-retina-large.jpg\",\n" +
            "\t\t\t\"id\": 5573137,\n" +
            "\t\t\t\"name\": \"A9 French Toast\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"price\": 900,\n" +
            "\t\t\t\"description\": \"\",\n" +
            "\t\t\t\"img_url\": \"https://res.cloudinary.com/doordash/image/fetch/c_fill,w_1200,h_672,q_auto:eco,f_auto/https://doordash-static.s3.amazonaws.com/media/photos/1e415e65-8d18-4146-8d6b-affdab761871-retina-large.jpg\",\n" +
            "\t\t\t\"id\": 5573136,\n" +
            "\t\t\t\"name\": \"A8 Belgium Waffle\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"price\": 500,\n" +
            "\t\t\t\"description\": \"\",\n" +
            "\t\t\t\"img_url\": \"https://res.cloudinary.com/doordash/image/fetch/c_fill,w_1200,h_672,q_auto:eco,f_auto/https://doordash-static.s3.amazonaws.com/media/photos/343bf017-743f-474a-adf2-73700ea007a2-retina-large.jpg\",\n" +
            "\t\t\t\"id\": 5573259,\n" +
            "\t\t\t\"name\": \"Iced Thai Tea\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"price\": 1150,\n" +
            "\t\t\t\"description\": \"Topped with three cheeses.\",\n" +
            "\t\t\t\"img_url\": \"https://res.cloudinary.com/doordash/image/fetch/c_fill,w_1200,h_672,q_auto:eco,f_auto/https://doordash-static.s3.amazonaws.com/media/photos/36cb08f4-c2d6-4656-8440-914bc98031f7-retina-large.jpg\",\n" +
            "\t\t\t\"id\": 5573164,\n" +
            "\t\t\t\"name\": \"D1 Tres Quesos\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"price\": 1375,\n" +
            "\t\t\t\"description\": \"Tender steak slices, mushrooms, onions & melted Swiss cheese.\",\n" +
            "\t\t\t\"img_url\": \"https://res.cloudinary.com/doordash/image/fetch/c_fill,w_1200,h_672,q_auto:eco,f_auto/https://doordash-static.s3.amazonaws.com/media/photos/6007af37-9897-452d-a961-60a091ad9b30-retina-large.jpg\",\n" +
            "\t\t\t\"id\": 5579959,\n" +
            "\t\t\t\"name\": \"K15 Philly Cheese Steak\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"price\": 1150,\n" +
            "\t\t\t\"description\": \"Three eggs scrambled with melted Cheddar cheese.\",\n" +
            "\t\t\t\"img_url\": \"https://res.cloudinary.com/doordash/image/fetch/c_fill,w_1200,h_672,q_auto:eco,f_auto/https://doordash-static.s3.amazonaws.com/media/photos/55801fb4-b0fd-40af-bf9c-558dfffae14b-retina-large.jpg\",\n" +
            "\t\t\t\"id\": 5573149,\n" +
            "\t\t\t\"name\": \"B2 Cheesy Scramble\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"price\": 1400,\n" +
            "\t\t\t\"description\": \"Scramble eggs, avocado, black beans & potato.\",\n" +
            "\t\t\t\"img_url\": \"https://res.cloudinary.com/doordash/image/fetch/c_fill,w_1200,h_672,q_auto:eco,f_auto/https://doordash-static.s3.amazonaws.com/media/photos/cd20c537-4a70-4a48-8516-480ff79ecedb-retina-large.jpg\",\n" +
            "\t\t\t\"id\": 5577239,\n" +
            "\t\t\t\"name\": \"#16 Veggie & Eggs\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"price\": 1025,\n" +
            "\t\t\t\"description\": \"6 chicken nuggets and French fries.\",\n" +
            "\t\t\t\"img_url\": \"https://res.cloudinary.com/doordash/image/fetch/c_fill,w_1200,h_672,q_auto:eco,f_auto/https://doordash-static.s3.amazonaws.com/media/photos/86d1093c-9452-40c6-9c6c-326ece6f169c-retina-large.jpg\",\n" +
            "\t\t\t\"id\": 5573244,\n" +
            "\t\t\t\"name\": \"J5 Chicken Nuggets & Fries\"\n" +
            "\t\t}],\n" +
            "\t\t\"is_catering\": false,\n" +
            "\t\t\"subtitle\": \"All Day\",\n" +
            "\t\t\"id\": 73940,\n" +
            "\t\t\"name\": \"A Good Morning (All Day) (Los Altos)\"\n" +
            "\t}],\n" +
            "\t\"composite_score\": 9,\n" +
            "\t\"status_type\": \"open\",\n" +
            "\t\"is_only_catering\": false,\n" +
            "\t\"status\": \"32 mins\",\n" +
            "\t\"number_of_ratings\": 6889,\n" +
            "\t\"asap_time\": 32,\n" +
            "\t\"description\": \"American, Breakfast & Brunch\",\n" +
            "\t\"business\": {\n" +
            "\t\t\"id\": 447,\n" +
            "\t\t\"name\": \"A Good Morning Cafe\"\n" +
            "\t},\n" +
            "\t\"tags\": [\"American (Traditional)\"],\n" +
            "\t\"yelp_review_count\": 466,\n" +
            "\t\"business_id\": 447,\n" +
            "\t\"extra_sos_delivery_fee\": 0,\n" +
            "\t\"yelp_rating\": 3.5,\n" +
            "\t\"cover_img_url\": \"https://cdn.doordash.com/media/restaurant/cover/A-Good-Morning-Cafe.png\",\n" +
            "\t\"header_img_url\": \"https://res.cloudinary.com/doordash/image/fetch/c_fill,w_1200,h_672,q_auto:eco,f_auto/https://doordash-static.s3.amazonaws.com/media/photos/2fab4d9a-3277-4e21-a889-898ad98d2333-retina-large.jpg\",\n" +
            "\t\"address\": {\n" +
            "\t\t\"city\": \"Los Altos\",\n" +
            "\t\t\"state\": \"CA\",\n" +
            "\t\t\"street\": \"4546 El Camino Real\",\n" +
            "\t\t\"lat\": 37.4006814,\n" +
            "\t\t\"lng\": -122.1146171,\n" +
            "\t\t\"printable_address\": \"4546 El Camino Real, Village Court Shopping Center, Los Altos, CA 94022, USA\"\n" +
            "\t},\n" +
            "\t\"price_range\": 2,\n" +
            "\t\"slug\": \"a-good-morning-cafe-los-altos\",\n" +
            "\t\"name\": \"A Good Morning Café\",\n" +
            "\t\"is_newly_added\": false,\n" +
            "\t\"url\": \"/store/a-good-morning-cafe-los-altos-443/\",\n" +
            "\t\"service_rate\": 11.0,\n" +
            "\t\"promotion\": null,\n" +
            "\t\"featured_category_description\": null\n" +
            "}";

    private List<Restaurant> restaurants = new ArrayList<>();
    private Restaurant restaurant;
    private Gson gson;
    private RestaurantListAdapter restaurantListAdapter;

    @Before
    public void setupAdapter() {
        MockitoAnnotations.initMocks(this);

        restaurantListAdapter = new RestaurantListAdapter();
        gson = new Gson();

        restaurant = new Restaurant();
        restaurant.setId((long)443);
        restaurant.setName("A Good Morning Café");
        restaurant.setDescription("American, Breakfast & Brunch");
        restaurant.setDeliveryFee(0.0);
        restaurant.setStatus("32 mins");
        restaurant.setCoverImageUrl("https://cdn.doordash.com/media/restaurant/cover/A-Good-Morning-Cafe.png");
    }

    @Test
    public void parseJsonToRestaurants() {
        Restaurant parsed = gson.fromJson(JSON, Restaurant.class);
        Assert.assertEquals(restaurant.getId(), parsed.getId());
        Assert.assertEquals(restaurant.getName(), parsed.getName());
        Assert.assertEquals(restaurant.getDescription(), parsed.getDescription());
        Assert.assertEquals(restaurant.getDeliveryFee(), parsed.getDeliveryFee());
        Assert.assertEquals(restaurant.getStatus(), parsed.getStatus());
    }

    @Test
    public void loadRestaurantsToAdapter() {
        Restaurant parsedRestaurant = gson.fromJson(JSON, Restaurant.class);
        restaurants.add(parsedRestaurant);
        restaurantListAdapter.setRestaurantList(restaurants);
        Assert.assertEquals(restaurantListAdapter.getItemCount(), restaurants.size());

    }




}
