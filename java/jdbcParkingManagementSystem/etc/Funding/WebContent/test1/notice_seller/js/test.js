
                    var v_fileBtn = document.querySelector("#id_file");
                    var v_disp = document.querySelector("#id_disp");
                     v_fileBtn.onchange = function(){
                         console.log(v_fileBtn.files[0].name);
                         var v_file = v_fileBtn.files[0];
                         var v_fileReader = new v_FileReader();
                         v_fileReader.readAsText();
                         v_fileReader.onload = function(){ 
                             console.log(v_fileReader.result)
                             var v_img = document.createElement("img");
                             v_img.src = v_fileReader.result;
                             v_img.width=200;
                             v_img.setAttribute("height","200");
                             v_disp.appendChild(v_img);
                         }
                         v_fileReader.readAsDataURL(v_file);
                     }
