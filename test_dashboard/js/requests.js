
/*$(document).ready(function(){

  const url="http://localhost:8081/GEI/classrooms/info";

  $.get(url, function(data, status){
      console.log('${data}')
      $('#a').html(data);
  });

});


$(document).ready(function(){
  const url='http://localhost:8081/GEI/classrooms/info';
  $.getJSON(url, function(data) {

        var text = `name: ${data.name}<br>
                    port: ${data.port}<br>`
        $("#mypanel").html(text);
    });
  });*/
$(document).ready(function(){
  $.ajax({
    crossDomain:true,
    type: "GET",
    url: "http://localhost:8081/GEI/Room1/info",
    success: function (data) {
      var text = `<b>${data.name}</b><br>
                  Temperature: ${data.temp.value} °C<br>
                  Presence: `;
      if(`${data.presence.value}`==0){
        text+=`none<br>`;
      }
      //LIGHTS
      for (var i = 0; i < `${data.lights.length}`; i++) {
        text+=`Light `+(i+1)+` : `
        if(`${data.lights[i].value}`==0){
          text+=`OFF<br>`;
        }else{
          text+=`ON<br>`;
        }
      }
      //WINDOWS
      for (var i = 0; i < `${data.windows.length}`; i++) {
        text+=`Window `+(i+1)+` : `;
        if(`${data.windows[i].value}`==0){
          text+=`CLOSED<br>`;
        }else{
            text+=`OPEN<br>`;
          }
      }
      //DOORS
      for (var i = 0; i < `${data.doors.length}`; i++) {
        text+=`Door `+(i+1)+` : `
        if(`${data.doors[i].value}`==0){
          text+=`CLOSED<br>`;
        }else{
            text+=`OPEN<br>`;
          }
      }
      //HEATING
      for (var i = 0; i < `${data.heating.length}`; i++) {
        text+=`Heating `+(i+1)+` : `;
        if(`${data.heating[i].value}`==0){
          text+=`OFF<br>`;
        }else{
          text+=`ON<br>`;
        }
      }
      $("#Room1").html(text);
    }
  });
});
