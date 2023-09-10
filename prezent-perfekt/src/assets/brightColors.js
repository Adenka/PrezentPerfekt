export const brightColors = [
    "grey-lighten-2",
    "grey-lighten-6",
    "cyan-accent-1",
    "cyan-accent-2",
    "pink-accent-1",
    "pink-accent-2",
    "purple-accent-1",
    "purple-accent-2"
]

export const getBrightColor = () => {
    return brightColors[Math.floor(Math.random() * brightColors.length)];
}

export const getBrightColorsArray = (arrayLength) => {
    const k = brightColors.length;

    let colorNumberArray = [];
    let arrayLengthDivisible = arrayLength + (k - (arrayLength % k));
    Array.from(Array(arrayLengthDivisible)).map((_, index) => {
        colorNumberArray.push(index % k);
    });

    for (let i = 0; i < arrayLengthDivisible; ++i) {
        let offset = 0;
        if (i % k === 0 && i > 0) {
            offset = Math.floor(Math.random() * (k - 1));
            if (offset >= colorNumberArray[i - 1]) {
                offset++;
            }
        }
        else {
            offset = Math.floor(Math.random() * (k - (i % k)));
        }
            
        let temp = colorNumberArray[i];
        colorNumberArray[i] = colorNumberArray[i + offset];
        colorNumberArray[i + offset] = temp;
    }

    let outputArray = [];
    colorNumberArray.map((colorNumber) => {
        outputArray.push(brightColors[colorNumber]);
    })

    return outputArray.slice(0, arrayLength);

}