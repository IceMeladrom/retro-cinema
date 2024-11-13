document.getElementById('cinema-hall').addEventListener('change', hallsSize);

function hallsSize(){
    let numOfHalls = document.getElementById('cinema-hall').value;
    let inputs = document.getElementsByClassName('cinema-hall-size');
    Array.from(inputs).forEach((el) => {el.remove();});

    for (let i = 1; i <= numOfHalls; i++){
        let newHall = document.createElement('div');
        newHall.setAttribute('class', 'cinema-hall-size')

        let hallRowText = document.createElement('p');
        hallRowText.innerText = `Количество рядов в зале ${i}`;

        let hallRowLabel = document.createElement('label');
        hallRowLabel.setAttribute('for', `cinema-hall-${i}-row`);

        let hallRowInput = document.createElement('input');
        hallRowInput.setAttribute('type', 'number');
        hallRowInput.setAttribute('id', `cinema-hall-${i}-row`);
        hallRowInput.setAttribute('name', `cinema-hall-${i}-row`);
        hallRowInput.setAttribute('required', 'true');
        hallRowInput.setAttribute('min', '1');
        hallRowInput.setAttribute('oninput', 'validity.valid||(value="");');

        newHall.appendChild(hallRowText);
        newHall.appendChild(hallRowLabel);
        newHall.appendChild(hallRowInput);

        let hallColText = document.createElement('p');
        hallColText.innerText = `Количество мест в ряду зала ${i}`;

        let hallColLabel = document.createElement('label');
        hallColLabel.setAttribute('for', `cinema-hall-${i}-col`);

        let hallColInput = document.createElement('input');
        hallColInput.setAttribute('type', 'number');
        hallColInput.setAttribute('id', `cinema-hall-${i}-col`);
        hallColInput.setAttribute('name', `cinema-hall-${i}-col`);
        hallColInput.setAttribute('required', 'true');
        hallColInput.setAttribute('min', '1');
        hallColInput.setAttribute('oninput', 'validity.valid||(value="");');


        newHall.appendChild(hallColText);
        newHall.appendChild(hallColLabel);
        newHall.appendChild(hallColInput);

        document.getElementById('cinema-form').appendChild(newHall);
    }


}

