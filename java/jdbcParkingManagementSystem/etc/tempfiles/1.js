        //스크립트로 제어하는 스타일은 해당 캐그의 인라인 스타일이다.
        var v_disp = document.querySelector("#id_disp");
        var v_colors = ["blue", "red", "green", "black", "magenta", "pink"];
        function f_click() {
            if (v_disp.style.display == "none") {
                v_disp.style.display = "block";
                return;
            } v_disp.style.display = "none"


        }
