export const brightColors = [
    "yellow-accent-2",
    "yellow-accent-3",
    "orange-darken-1",
    "lime-accent-3",
    "light-green-accent-3",
    "cyan-accent-1",
    "light-blue-accent-1",
    "pink-accent-1",
    "pink-accent-2",
    "purple-accent-1",
]

export const getBrightColor = () => {
    return brightColors[Math.floor(Math.random() * brightColors.length)];
}