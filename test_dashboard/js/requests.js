
  function actuatorCheck(selectObject) {
    console.log(selectObject.id);
    var data;
    var url="http://localhost:8083/interface/post/"+selectObject.id+"/";
    console.log(url);
    if(selectObject.checked === true){
      $.ajax({
        crossDomain:true,
        type: "GET",
        url: url+"1",
        success: function(msg) {
        }
      });
    } else {
      $.ajax({
        crossDomain:true,
        type: "GET",
        url: url+"0",
        success: function(msg) {
        }
      });
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
          text+=`<input type="checkbox" id="Room1/Light`+(i+1)+`" onchange="actuatorCheck(this)">`;
        }else{
          text+=`<input type="checkbox" id="Room1/Light`+(i+1)+`" onchange="actuatorCheck(this)" checked>`;
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
          text+=`<input type="checkbox" id="Room2/Light`+(i+1)+`" onchange="actuatorCheck(this)">`;
        }else{
          text+=`<input type="checkbox" id="Room2/Light`+(i+1)+`" onchange="actuatorCheck(this)" checked>`;
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
          text+=`<input type="checkbox" id="Room1/Window`+(i+1)+`" onchange="actuatorCheck(this)">`;
        }else{
          text+=`<input type="checkbox" id="Room1/Window`+(i+1)+`" onchange="actuatorCheck(this)" checked>`;
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
          text+=`<input type="checkbox" id="Room2/Window`+(i+1)+`" onchange="actuatorCheck(this)">`;
        }else{
          text+=`<input type="checkbox" id="Room2/Window`+(i+1)+`" onchange="actuatorCheck(this)" checked>`;
        }
        text+=`<span class="slider round"></span>`
        text+=`</label>`
      }
      $("#Windows2").html(text);
    }
  });

    $.ajax({
          crossDomain:true,
          type: "GET",
          url: "http://localhost:8081/GEI/tempExt",
          success: function (data) {
            var text=`<b>${data} °C</b>`;

            $("#tempExt").html(text);
          }
    });


});
