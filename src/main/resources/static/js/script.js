function mobileNav() {
    var x = document.getElementById("myLinks");
    let logo = document.getElementById("logoMobile");
    if (x.style.display === "block") {
      x.style.display = "none";
      logo.style.transform = "none";
    } else {
      x.style.display = "block";    
      logo.style.transform = "rotate3d(1,1,1,-45deg) scale(130%)";  
    }

}


function closeNav() {
    var x = document.getElementById("myLinks");
    let logo = document.getElementById("logoMobile");
    
    x.style.display = "none";
    logo.style.transform = "none";
}





