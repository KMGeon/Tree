     //재귀호출 , 종료시점이 중요
        //1~특정숫자까지의 합
        function f_sum(N) {
            if (N == 1) {
                return 1;
            }
            returnN * f_sum(N - 1); //factorial 10!
        }
        alert(f_sum(100));


        function f_clock() {

            var date = new Date();
            var year = date.getFullYear();
            var month = date.getMonth() + 1;
            var hour = date.getHours();
            var minute = date.getMinutes();
            var second = date.getSeconds();

            document.getElementById("id_clock").innerHTML = year + "년" + month + "월" + " " + hour + "시" + + minute + "분" + second + "초";
        }
        f_clock();
        setInterval(f_clock, 1000);