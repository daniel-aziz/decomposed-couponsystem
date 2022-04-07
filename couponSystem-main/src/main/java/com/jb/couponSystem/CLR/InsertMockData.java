package com.jb.couponSystem.CLR;

import com.jb.couponSystem.Beans.*;
import com.jb.couponSystem.Repositories.CategoryEntityRepository;
import com.jb.couponSystem.Services.AdminService;
import com.jb.couponSystem.Services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Order(1)
@RequiredArgsConstructor
public class InsertMockData implements CommandLineRunner {
    private final AdminService adminService;
    private final CompanyService companyService;
    private final CategoryEntityRepository categoryEntityRepository;

    /**
     * List of Companies
     *
     * @return ArrayList of Companies
     */
    private ArrayList<Company> listOfCompanies() {
        Company hotels = Company.builder()
                .name("Hotels")
                .email("hotels@hotels.com")
                .password("hotels")
                .build();

        Company microchip = Company.builder()
                .name("Microchips")
                .email("Microchips@Microchips.com")
                .password("microchips")
                .build();

        Company jnj = Company.builder()
                .name("John & John")
                .email("jnj@jnj.com")
                .password("jnj12")
                .build();

        Company intec = Company.builder()
                .name("Intec")
                .email("Intec@Intec.com")
                .password("intec")
                .build();
        Company telso = Company.builder()
                .name("Telso")
                .email("Telso@Telso.com")
                .password("telso")
                .build();
        Company acoc = Company.builder()
                .name("Acoc")
                .email("Acoc@Acoc.com")
                .password("acoc1")
                .build();
        Company samson9 = Company.builder()
                .name("Samson9")
                .email("Samson9@Samson9.com")
                .password("samson9")
                .build();
        Company banana = Company.builder()
                .name("Banana")
                .email("Banana@Banana.com")
                .password("banana")
                .build();

        return new ArrayList<>() {{
            add(hotels); // id 1
            add(microchip); // id 2
            add(jnj); // id 3
            add(intec); // id 4
            add(telso); // id 5
            add(acoc); // id 6
            add(samson9); // id 7
            add(banana); // id 8
        }};
    }

    /**
     * List of Customers
     *
     * @return ArrayList of Customers
     */
    private ArrayList<Customer> listOfCustomers() {
        Customer daniel = Customer.builder()
                .firstName("Daniel")
                .lastName("Daniel")
                .email("daniel@daniel.com")
                .password("daniel1")
                .build();

        Customer moshe = Customer.builder()
                .firstName("Moshe")
                .lastName("Moshe")
                .email("moshe@moshe.com")
                .password("moshe1")
                .build();

        Customer arik = Customer.builder()
                .firstName("Arik")
                .lastName("Arik")
                .email("arik@arik.com")
                .password("pass3")
                .build();

        Customer liya = Customer.builder()
                .firstName("Liya")
                .lastName("Liya")
                .email("liya@liya.com")
                .password("liya1")
                .build();
        Customer yoni = Customer.builder()
                .firstName("Yoni")
                .lastName("Yoni")
                .email("yoni@yoni.com")
                .password("yoni1")
                .build();
        Customer nir = Customer.builder()
                .firstName("Nir")
                .lastName("Nir")
                .email("nir@nir.com")
                .password("nir1")
                .build();
        Customer tamir = Customer.builder()
                .firstName("Tamir")
                .lastName("Tamir")
                .email("tamir@tamir.com")
                .password("tamir1")
                .build();
        Customer yael = Customer.builder()
                .firstName("Yael")
                .lastName("Yael")
                .email("yael@yael.com")
                .password("yael1")
                .build();
        Customer sanji = Customer.builder()
                .firstName("Sanji")
                .lastName("Sanji")
                .email("sanji@sanji.com")
                .password("sanji1")
                .build();

        return new ArrayList<>() {{
            add(daniel);
            add(moshe);
            add(arik);
            add(liya);
            add(yoni);
            add(nir);
            add(tamir);
            add(yael);
            add(sanji);
        }};
    }

    /**
     * List of Coupons
     *
     * @return ArrayList of Coupons
     */
    private ArrayList<Coupon> listOfCoupons() {
        Coupon automotive1 = Coupon.builder().company(Company.builder().id(5).build())
                .category(Category.Automotive)
                .title("New Teslo Car")
                .description("Buy online with 100% finance")
                .startDate(Date.valueOf("2021-10-20"))
                .endDate(Date.valueOf("2022-10-01"))
                .amount(5)
                .price(25000)
                .image("https://i.ibb.co/JkkhTvQ/toyota.jpg")
                .build();

        Coupon beauty1 = Coupon.builder().company(Company.builder().id(6).build())
                .category(Category.Beauty)
                .title("Hand Lotion")
                .description("Made for men, 50% Lotion, 50% Car Grease")
                .startDate(Date.valueOf("2021-07-01"))
                .endDate(Date.valueOf("2022-10-01"))
                .amount(50)
                .price(15)
                .image("https://i.ibb.co/L01gTRw/lotion.jpg")
                .build();

        Coupon clothing1 = Coupon.builder().company(Company.builder().id(6).build())
                .category(Category.Clothing)
                .title("Blue Business Suit")
                .description("Italian hand made suite from cotton")
                .startDate(Date.valueOf("2021-07-01"))
                .endDate(Date.valueOf("2022-10-01"))
                .amount(1000)
                .price(450)
                .image("https://i.ibb.co/mFrYsQQ/buissnece-Suit.jpg")
                .build();

        Coupon electronics1 = Coupon.builder().company(Company.builder().id(2).build())
                .category(Category.Electronics)
                .title("GeForce RTX 3060 TI")
                .description("1 Day Delivery, 5 Years of insurance")
                .startDate(Date.valueOf("2021-07-01"))
                .endDate(Date.valueOf("2025-10-01"))
                .amount(15)
                .price(1050)
                .image("https://i.ibb.co/NTxFCJK/nvidia-Card.jpg")
                .build();
        Coupon entertainment1 = Coupon.builder().company(Company.builder().id(3).build())
                .category(Category.Entertainment)
                .title("1 + 1 For Weekend Movies")
                .description("Thursday - Saturday")
                .startDate(Date.valueOf("2021-12-01"))
                .endDate(Date.valueOf("2023-12-01"))
                .amount(1000)
                .price(15)
                .image("https://i.ibb.co/Hxx0ZK3/movie-Theater.jpg")
                .build();
        Coupon financial1 = Coupon.builder().company(Company.builder().id(4).build())
                .category(Category.Financial)
                .title("Financial Counseling")
                .description("Financial counseling for new Families")
                .startDate(Date.valueOf("2021-07-01"))
                .endDate(Date.valueOf("2022-10-01"))
                .amount(50)
                .price(100)
                .image("https://i.ibb.co/Nr9LMVs/Financial-Counseling.jpg")
                .build();
        Coupon fitness1 = Coupon.builder().company(Company.builder().id(8).build())
                .category(Category.Fitness)
                .title("Running Machine")
                .description("Mid size running machine, for all ages")
                .startDate(Date.valueOf("2021-07-01"))
                .endDate(Date.valueOf("2022-10-01"))
                .amount(85)
                .price(575)
                .image("https://i.ibb.co/j63V8Hh/running-Machine.jpg")
                .build();
        Coupon food1 = Coupon.builder().company(Company.builder().id(3).build())
                .category(Category.Food)
                .title("Dinner in a 3 Michelin Star restaurant")
                .description("2 persons full course dinner at Moyka")
                .startDate(Date.valueOf("2021-07-01"))
                .endDate(Date.valueOf("2022-10-01"))
                .amount(20)
                .price(250)
                .image("https://i.ibb.co/LZ88Ppk/resturant.jpg")
                .build();
        Coupon garden1 = Coupon.builder().company(Company.builder().id(5).build())
                .category(Category.Garden)
                .title("Renovate your garden")
                .description("Full Renovation including furniture, Price can range")
                .startDate(Date.valueOf("2021-07-01"))
                .endDate(Date.valueOf("2022-10-01"))
                .amount(20)
                .price(25000)
                .image("https://i.ibb.co/Pz8zMZD/garden.jpg")
                .build();
        Coupon general1 = Coupon.builder().company(Company.builder().id(5).build())
                .category(Category.General)
                .title("50% Discount on all items")
                .description("All store in the country")
                .startDate(Date.valueOf("2021-07-01"))
                .endDate(Date.valueOf("2022-10-01"))
                .amount(5000)
                .price(1)
                .image("https://i.ibb.co/M57Vqj9/appliance-Store.jpg")
                .build();
        Coupon gifts1 = Coupon.builder().company(Company.builder().id(6).build())
                .category(Category.Gifts)
                .title("Iphone 13")
                .description("Gifts & More")
                .startDate(Date.valueOf("2021-07-01"))
                .endDate(Date.valueOf("2022-10-01"))
                .amount(0)
                .price(600)
                .image("https://i.ibb.co/cvWFkHV/iphone.jpg")
                .build();
        Coupon health1 = Coupon.builder().company(Company.builder().id(7).build())
                .category(Category.Health)
                .title("Health Watch")
                .description("Health Watch Men/Women")
                .startDate(Date.valueOf("2021-07-01"))
                .endDate(Date.valueOf("2022-10-01"))
                .amount(2000)
                .price(150)
                .image("https://i.ibb.co/b56Ht6G/health-Watch.jpg")
                .build();
        Coupon home1 = Coupon.builder().company(Company.builder().id(3).build())
                .category(Category.Home)
                .title("Living room Sofa")
                .description("Best fabric in the world")
                .startDate(Date.valueOf("2021-07-01"))
                .endDate(Date.valueOf("2022-10-01"))
                .amount(200)
                .price(1575)
                .image("https://i.ibb.co/vQJPnY2/sofa.jpg")
                .build();
        Coupon jewelry1 = Coupon.builder().company(Company.builder().id(7).build())
                .category(Category.Jewelry)
                .title("Wedding Rings")
                .description("Diamond Only")
                .startDate(Date.valueOf("2021-07-01"))
                .endDate(Date.valueOf("2022-10-01"))
                .amount(150)
                .price(3550)
                .image("https://i.ibb.co/M64wfDM/rings.jpg")
                .build();
        Coupon travel1 = Coupon.builder().company(Company.builder().id(1).build())
                .category(Category.Travel)
                .title("3 nights in Rome")
                .description("City Center, 5 Start Hotel, Including Breakfast")
                .startDate(Date.valueOf("2021-07-01"))
                .endDate(Date.valueOf("2022-10-01"))
                .amount(1000)
                .price(1500)
                .image("https://i.ibb.co/SBmFkzQ/rome.jpg")
                .build();


        return new ArrayList<>() {{
            add(automotive1);
            add(beauty1);
            add(clothing1);
            add(electronics1);
            add(entertainment1);
            add(financial1);
            add(fitness1);
            add(food1);
            add(garden1);
            add(general1);
            add(gifts1);
            add(health1);
            add(home1);
            add(jewelry1);
            add(travel1);
        }};

    }

    private List<String> listOfCategories() {
        List<String> categoryEntities = Arrays.stream(Category.values())
                .map(Category::getDescription)
                .collect(Collectors.toList());
        return categoryEntities;
    }

    @Override
    public void run(String... args) {
        try {
            for (Company item : listOfCompanies()) {
                adminService.addCompany(item);
            }
        } catch (Exception e) {
            printIssue("Company");
        }

        try {
            for (Customer item : listOfCustomers()) {
                adminService.addCustomer(item);
            }
        } catch (Exception e) {
            printIssue("Customer");
        }

        try {
            for (Coupon item : listOfCoupons()) {
                companyService.setCompanyId(item.getCompany().getId());
                companyService.addCoupon(item);
            }
        } catch (Exception e) {
            printIssue("Coupon");
        }

        try {
            categoryEntityRepository.deleteAll();
            listOfCategories().forEach((item) -> {
                CategoryEntity category = CategoryEntity.builder().id(Category.valueOf(item).ordinal() + 1).category(item).build();
                categoryEntityRepository.save(category);
            });
        } catch (Exception e) {
            printIssue("Category");
        }

    }

    private void printIssue(String object) {
        System.out.println("=====================================ALERT===================================");
        System.out.println(object + " duplicate entries issue: ");
        System.out.println("disable @Component in com.jb.couponSystem.CLR.InsertMockData Class");
        System.out.println("=============================================================================");
        System.out.println();
    }
}
