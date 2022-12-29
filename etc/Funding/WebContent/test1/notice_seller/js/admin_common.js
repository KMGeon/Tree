$(document).ready(function() {



   

    $(window).scroll(function(){
        var scrollTop = $(document).scrollTop();
        if (scrollTop < 264) {
         scrollTop = 264;
        }
        $(".snb_my").stop();
        $(".snb_my").animate( { "top" : scrollTop });
        });



    





});