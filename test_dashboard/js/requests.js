
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
  function room1LightCheck(selectObject) {
    if(selectObject.checked === true && selectObject.id == "Light1"){
      selectObject.checked === false;
    } else {

    }
  }

  function windowCheck(selectObject) {
    if(selectObject.checked === true){

    } else {
      
    }
  }


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
                  } else {
                    text+=`yes<br>`;
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

  $.ajax({
    crossDomain:true,
    type: "GET",
    url: "http://localhost:8081/GEI/Room2/info",
    success: function (data) {
      var text = `<b>${data.name}</b><br>
                  Temperature: ${data.temp.value} °C<br>
                  Presence: `;
      if(`${data.presence.value}`==0){
        text+=`none<br>`;
      } else {
        text+=`yes<br>`;
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
      $("#Room2").html(text);
    }
  });

  $.ajax({
    crossDomain:true,
    type: "GET",
    url: "http://localhost:8081/GEI/Room1/info",
    success: function (data) {
      var text = `<b>${data.name}</b><br><br>`;
      //LIGHTS
      for (var i = 0; i < `${data.lights.length}`; i++) {
        text+=`<h5>Light `+(i+1)+`</h5>`
        text+=`<label class="switch" float="right">`
        if(`${data.lights[i].value}`==0){
          text+=`<input type="checkbox" id="Light`+(i+1)+`" onchange="room1LightCheck(this)">`;
        }else{
          text+=`<input type="checkbox" id="Light`+(i+1)+`" onchange="room1LightCheck(this)" checked>`;
        }
        text+=`<span class="slider round"></span>`
        text+=`</label>`
      }
      $("#Lights1").html(text);
    }
  });

  $.ajax({
    crossDomain:true,
    type: "GET",
    url: "http://localhost:8081/GEI/Room2/info",
    success: function (data) {
      var text = `<b>${data.name}</b><br><br>`;
      //LIGHTS
      for (var i = 0; i < `${data.lights.length}`; i++) {
        text+=`<h5>Light `+(i+1)+`</h5>`
        text+=`<label class="switch" float="right">`
        if(`${data.lights[i].value}`==0){
          text+=`<input type="checkbox" id="Light`+(i+1)+`" onchange="lightCheck(this)">`;
        }else{
          text+=`<input type="checkbox" id="Light`+(i+1)+`" onchange="lightCheck(this)" checked>`;
        }
        text+=`<span class="slider round"></span>`
        text+=`</label>`
      }
      $("#Lights2").html(text);
    }
  });

  $.ajax({
    crossDomain:true,
    type: "GET",
    url: "http://localhost:8081/GEI/Room1/info",
    success: function (data) {
      var text = `<b>${data.name}</b><br><br>`;
      //WINDOWS
      for (var i = 0; i < `${data.windows.length}`; i++) {
        text+=`<h5>Window `+(i+1)+`</h5>`
        text+=`<label class="switch" float="right">`
        if(`${data.windows[i].value}`==0){
          text+=`<input type="checkbox" id="Window`+(i+1)+`" onchange="windowCheck(this)">`;
        }else{
          text+=`<input type="checkbox" id="Window`+(i+1)+`" onchange="windowCheck(this)" checked>`;
        }
        text+=`<span class="slider round"></span>`
        text+=`</label>`
      }
      $("#Windows1").html(text);
    }
  });

  $.ajax({
    crossDomain:true,
    type: "GET",
    url: "http://localhost:8081/GEI/Room2/info",
    success: function (data) {
      var text = `<b>${data.name}</b><br><br>`;
      //WINDOWS
      for (var i = 0; i < `${data.windows.length}`; i++) {
        text+=`<h5>Window `+(i+1)+`</h5>`
        text+=`<label class="switch" float="right">`
        if(`${data.windows[i].value}`==0){
          text+=`<input type="checkbox" id="Window`+(i+1)+`" onchange="windowCheck(this)">`;
        }else{
          text+=`<input type="checkbox" id="Window`+(i+1)+`" onchange="windowCheck(this)" checked>`;
        }
        text+=`<span class="slider round"></span>`
        text+=`</label>`
      }
      $("#Windows2").html(text);
    }
  });
});
