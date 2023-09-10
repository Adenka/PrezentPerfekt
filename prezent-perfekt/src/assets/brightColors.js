export const brightColors = [
    "grey-lighten-2",
    "grey-lighten-3",
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
    
}