$(document).ready(function(){


    if($('.list_order li').length == 0){   // 주문 내역이 없으면 "주문내역이 없습니다" 화면 띄우기
        $('.no_order_data').css('display', 'block');
    }
    else{
        $('.no_order_data').css('display', 'none');
    }


    

    $('.search_date .btn_layer').click(function(){     
        $('.search_date .layer_search').slideToggle( '1000' );   
        
    });

    $('.order_state .btn_layer').click(function(){     
        $('.order_state .layer_search').slideToggle( '1000' );       
        
    });








});