export default function Section({children,  ...props}) {
    return (
        <section {...props}>
            <h1>{props.title}</h1>
            {children}
        </section>
    );
}
