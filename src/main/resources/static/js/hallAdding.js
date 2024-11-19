document.getElementById('cinema-hall').addEventListener('change', hallsSize);

function hallsSize(){
    let numOfHalls = document.getElementById('cinema-hall').value;
    let inputs = document.getElementsByClassName('cinema-hall-size');
    Array.from(inputs).forEach((el) => {el.remove();});

    for (let i = 0; i < numOfHalls; i++){
        let newHall = document.createElement('div');
        newHall.setAttribute('class', 'cinema-hall-size');

        let hallRowText = document.createElement('p');
        hallRowText.innerText = `Количество рядов в зале ${i + 1}`;

        let hallRowLabel = document.createElement('label');
        hallRowLabel.setAttribute('for', `halls[${i}].row`);

        let hallRowInput = document.createElement('input');
        hallRowInput.setAttribute('type', 'number');
        hallRowInput.setAttribute('id', `halls[${i}].row`);
        hallRowInput.setAttribute('name', `halls[${i}].row`);
        hallRowInput.setAttribute('required', 'true');
        hallRowInput.setAttribute('min', '1');
        hallRowInput.setAttribute('oninput', 'validity.valid||(value="");');

        newHall.appendChild(hallRowText);
        newHall.appendChild(hallRowLabel);
        newHall.appendChild(hallRowInput);

        let hallColText = document.createElement('p');
        hallColText.innerText = `Количество мест в ряду зала ${i + 1}`;

        let hallColLabel = document.createElement('label');
        hallColLabel.setAttribute('for', `halls[${i}].col`);

        let hallColInput = document.createElement('input');
        hallColInput.setAttribute('type', 'number');
        hallColInput.setAttribute('id', `halls[${i}].col`);
        hallColInput.setAttribute('name', `halls[${i}].col`);
        hallColInput.setAttribute('required', 'true');
        hallColInput.setAttribute('min', '1');
        hallColInput.setAttribute('oninput', 'validity.valid||(value="");');

        newHall.appendChild(hallColText);
        newHall.appendChild(hallColLabel);
        newHall.appendChild(hallColInput);

        let hallNumberHiddenInput = document.createElement('input');
        hallNumberHiddenInput.setAttribute('type', 'hidden');
        hallNumberHiddenInput.setAttribute('id', `halls[${i}].hallNumber`);
        hallNumberHiddenInput.setAttribute('name', `halls[${i}].hallNumber`);
        hallNumberHiddenInput.setAttribute('value', `${i + 1}`);
        hallNumberHiddenInput.setAttribute('required', 'true');
        hallNumberHiddenInput.setAttribute('min', '1');
        hallNumberHiddenInput.setAttribute('oninput', 'validity.valid||(value="");');

        newHall.appendChild(hallNumberHiddenInput);

        document.getElementById('cinema-form').appendChild(newHall);
    }
}
