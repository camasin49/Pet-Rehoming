package com.example.petrehoming;

public class Upload {
    private String namePet;
    private String genderPet;
    private String descriptionPet;
    private String mImageUrl;
    private String username;
    private String contactNo;

    public Upload(){
        //SHOULD BE EMPTY
    }
    public Upload(String name, String gender, String description, String user,String contact, String ImageUrl) {
        if(name.trim().equals("")){
            name = "No name";
        }
        if(gender.trim().equals("")){
            gender = "No gender";
        }
        if (description.trim().equals("")) {
            description = "No description";
        }
        if (contact.trim().equals("")){
            contact = "No Contact Number";
        }

        this.namePet = name;
        this.genderPet = gender;
        this.descriptionPet = description;
        this.username = user;
        this.contactNo = contact;
        this.mImageUrl = ImageUrl;
    }

    public String getName(){
        return namePet;
    }

    public void setName(String name){
        namePet = name;
    }

    public String getGender(){
        return genderPet;
    }

    public void setGender(String gender){
        genderPet = gender;
    }

    public String getDescription(){
        return descriptionPet;
    }

    public void setDescription(String description){
        descriptionPet = description;
    }

    public String getUser(){
        return username;
    }

    public void setUser(String user){
        username = user;
    }

    public String getContactNo(){
        return contactNo;
    }

    public void setContactNo(String contact){
        contactNo = contact;
    }

    public String getImageUrl(){
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl){
        mImageUrl = imageUrl;
    }
}
